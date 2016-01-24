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

/**
 * Encapsulation of an Auction. Instances will be re-used by {@link AuctionHouse}.
 */
public class Auction {
    private static final long LINGER_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(10);
    private byte[] nameInBytes = new byte[1024];
    private int nameLength;
    private int id;
    private long expiration;
    private long currentHighBid;
    private long currentHighBidderId;
    private State state;
    public Auction() {
        this.state = State.INACTIVE;
    }

    public void reset(final int id, final byte[] name, final int nameLength, final long expiration, final long reserveValue) {
        this.id = id;
        System.arraycopy(name, 0, this.nameInBytes, 0, name.length);
        this.nameLength = nameLength;
        this.expiration = expiration;
        this.currentHighBid = reserveValue;
        this.currentHighBidderId = Bidder.INVALID_BIDDER;
        this.state = State.ACTIVE;
    }

    public boolean isInactive() {
        return (State.INACTIVE == state);
    }

    public boolean isActive() {
        return (State.ACTIVE == state);
    }

    public int id() {
        return id;
    }

    public String name() {
        return new String(nameInBytes, 0, nameLength);
    }

    public byte[] nameInBytes() {
        return nameInBytes;
    }

    public int nameLength() {
        return nameLength;
    }

    public long expiration() {
        return expiration;
    }

    public long highBid() {
        return currentHighBid;
    }

    public long highBidder() {
        return currentHighBidderId;
    }

    // return 0 for nothing new or > 0 for activity
    public int onAdvanceTime(final long now) {
        int result = 0;

        if (State.ACTIVE == state && now > expiration) {
            state = State.OVER;
            result = 1;
        } else if (State.OVER == state && now > (expiration + LINGER_TIMEOUT_NANOS)) {
            state = State.INACTIVE;
        }

        return result;
    }

    // True if high bid. False if not.
    public boolean bid(final long bidderId, final long value) {
        boolean result = false;

        if (State.ACTIVE == state && value > currentHighBid) {
            currentHighBid = value;
            currentHighBidderId = bidderId;
            result = true;
        }

        return result;
    }

    public void cancel() {
        this.currentHighBidderId = Bidder.INVALID_BIDDER;
        this.state = State.OVER;
    }

    /*
     * INACTIVE means Auction not valid
     * ACTIVE means Auction is accepting bids
     * OVER means Auction is over (Auction will linger for a period of time before being re-used)
     */
    enum State {
        INACTIVE, ACTIVE, OVER
    }
}
