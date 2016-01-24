/*
 * Copyright 2015 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package amaethon;

import java.util.concurrent.TimeUnit;

import amaethon.generated.AuctionDecoder;
import amaethon.generated.BidDecoder;
import amaethon.generated.MessageHeaderDecoder;
import uk.co.real_logic.aeron.Aeron;
import uk.co.real_logic.aeron.Subscription;
import uk.co.real_logic.aeron.logbuffer.FragmentHandler;
import uk.co.real_logic.aeron.logbuffer.Header;
import uk.co.real_logic.agrona.CloseHelper;
import uk.co.real_logic.agrona.DirectBuffer;
import uk.co.real_logic.agrona.concurrent.BackoffIdleStrategy;
import uk.co.real_logic.agrona.concurrent.IdleStrategy;

public class AuctionService implements Runnable, AutoCloseable, FragmentHandler {
    private static final int MESSAGE_TEMPLATE_VERSION = 0;
    private static final long IDLE_MAX_SPINS = 0;
    private static final long IDLE_MAX_YIELDS = 0;
    private static final long IDLE_MIN_PARK_NS = TimeUnit.NANOSECONDS.toNanos(1);
    private static final long IDLE_MAX_PARK_NS = TimeUnit.MILLISECONDS.toNanos(1);

    private final MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
    private final AuctionDecoder auctionDecoder = new AuctionDecoder();
    private final BidDecoder bidDecoder = new BidDecoder();
    private final byte[] tmpByteArray = new byte[1024];
    private final IdleStrategy idleStrategy =
            new BackoffIdleStrategy(IDLE_MAX_SPINS, IDLE_MAX_YIELDS, IDLE_MIN_PARK_NS, IDLE_MAX_PARK_NS);

    private final AuctionHouse house;
    private final Aeron aeron;
    private final Subscription subscription;

    private volatile boolean running = true;

    public AuctionService(final String submissionChannel, final int submissionStreamId) {
        house = new AuctionHouse(
                (auction) -> System.out.format("new auction: name=%s\n", auction.name()),
                (auction) -> System.out.format(
                        "new high bid: name=%s, bidder=%d, bid=%d\n", auction.name(), auction.highBidder(), auction.highBid()),
                (auction) -> System.out.format(
                        "auction won: name=%s, bidder=%d, bid=%d\n", auction.name(), auction.highBidder(), auction.highBid()));

        // TODO: for exercise, add Aeron
        aeron = Aeron.connect(new Aeron.Context());
        // TODO: for exercise, add Subscription
        subscription = aeron.addSubscription(submissionChannel, submissionStreamId);
    }

    public AuctionHouse house() {
        return house;
    }

    public void shutdown() {
        running = false;
    }

    public void close() {
        CloseHelper.quietClose(subscription);
        CloseHelper.quietClose(aeron);
    }

    public void run() {
        while (running) {
            // TODO: for exercise, subscription polling
            final int fragmentsRead = subscription.poll(this, Integer.MAX_VALUE);
            final long now = System.nanoTime();

            house.advanceTime(now);

            idleStrategy.idle(fragmentsRead);
        }
    }

    public void onFragment(DirectBuffer buffer, int offset, int length, Header header) {
        // TODO: for exercise, handle data
        messageHeaderDecoder.wrap(buffer, offset, MESSAGE_TEMPLATE_VERSION);

        if (AuctionDecoder.TEMPLATE_ID == messageHeaderDecoder.templateId()) {
            auctionDecoder.wrap(
                    buffer,
                    offset + messageHeaderDecoder.size(),
                    messageHeaderDecoder.blockLength(),
                    MESSAGE_TEMPLATE_VERSION);

            final long now = System.nanoTime();
            final int nameLength = auctionDecoder.getName(tmpByteArray, 0, tmpByteArray.length);

            house.add(tmpByteArray, nameLength, now + auctionDecoder.durationInNanos(), auctionDecoder.reserve());
        } else if (BidDecoder.TEMPLATE_ID == messageHeaderDecoder.templateId()) {
            bidDecoder.wrap(
                    buffer,
                    offset + messageHeaderDecoder.size(),
                    messageHeaderDecoder.blockLength(),
                    MESSAGE_TEMPLATE_VERSION);

            house.bid(bidDecoder.auctionId(), bidDecoder.bidderId(), bidDecoder.value());
        }
    }
}
