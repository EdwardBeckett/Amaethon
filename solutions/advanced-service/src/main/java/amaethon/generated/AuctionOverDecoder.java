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

/* Generated SBE (Simple Binary Encoding) message codec */
package amaethon.generated;

import uk.co.real_logic.agrona.DirectBuffer;
import uk.co.real_logic.sbe.codec.java.CodecUtil;

public class AuctionOverDecoder {
    public static final int BLOCK_LENGTH = 24;
    public static final int TEMPLATE_ID = 5;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 0;

    private final AuctionOverDecoder parentMessage = this;
    protected int offset;
    protected int limit;
    protected int actingBlockLength;
    protected int actingVersion;
    private DirectBuffer buffer;

    public static int auctionIdId() {
        return 1;
    }

    public static String auctionIdMetaAttribute(final MetaAttribute metaAttribute) {
        switch (metaAttribute) {
            case EPOCH:
                return "unix";
            case TIME_UNIT:
                return "nanosecond";
            case SEMANTIC_TYPE:
                return "";
        }

        return "";
    }

    public static int auctionIdNullValue() {
        return -2147483648;
    }

    public static int auctionIdMinValue() {
        return -2147483647;
    }

    public static int auctionIdMaxValue() {
        return 2147483647;
    }

    public static int winningBidderIdId() {
        return 2;
    }

    public static String winningBidderIdMetaAttribute(final MetaAttribute metaAttribute) {
        switch (metaAttribute) {
            case EPOCH:
                return "unix";
            case TIME_UNIT:
                return "nanosecond";
            case SEMANTIC_TYPE:
                return "";
        }

        return "";
    }

    public static long winningBidderIdNullValue() {
        return -9223372036854775808L;
    }

    public static long winningBidderIdMinValue() {
        return -9223372036854775807L;
    }

    public static long winningBidderIdMaxValue() {
        return 9223372036854775807L;
    }

    public static int winningBidId() {
        return 3;
    }

    public static String winningBidMetaAttribute(final MetaAttribute metaAttribute) {
        switch (metaAttribute) {
            case EPOCH:
                return "unix";
            case TIME_UNIT:
                return "nanosecond";
            case SEMANTIC_TYPE:
                return "";
        }

        return "";
    }

    public static long winningBidNullValue() {
        return -9223372036854775808L;
    }

    public static long winningBidMinValue() {
        return -9223372036854775807L;
    }

    public static long winningBidMaxValue() {
        return 9223372036854775807L;
    }

    public static int quantityLeftId() {
        return 4;
    }

    public static String quantityLeftMetaAttribute(final MetaAttribute metaAttribute) {
        switch (metaAttribute) {
            case EPOCH:
                return "unix";
            case TIME_UNIT:
                return "nanosecond";
            case SEMANTIC_TYPE:
                return "";
        }

        return "";
    }

    public static int quantityLeftNullValue() {
        return -2147483648;
    }

    public static int quantityLeftMinValue() {
        return -2147483647;
    }

    public static int quantityLeftMaxValue() {
        return 2147483647;
    }

    public int sbeBlockLength() {
        return BLOCK_LENGTH;
    }

    public int sbeTemplateId() {
        return TEMPLATE_ID;
    }

    public int sbeSchemaId() {
        return SCHEMA_ID;
    }

    public int sbeSchemaVersion() {
        return SCHEMA_VERSION;
    }

    public String sbeSemanticType() {
        return "";
    }

    public int offset() {
        return offset;
    }

    public AuctionOverDecoder wrap(
            final DirectBuffer buffer, final int offset, final int actingBlockLength, final int actingVersion) {
        this.buffer = buffer;
        this.offset = offset;
        this.actingBlockLength = actingBlockLength;
        this.actingVersion = actingVersion;
        limit(offset + actingBlockLength);

        return this;
    }

    public int size() {
        return limit - offset;
    }

    public int limit() {
        return limit;
    }

    public void limit(final int limit) {
        buffer.checkLimit(limit);
        this.limit = limit;
    }

    public int auctionId() {
        return CodecUtil.int32Get(buffer, offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
    }

    public long winningBidderId() {
        return CodecUtil.int64Get(buffer, offset + 4, java.nio.ByteOrder.LITTLE_ENDIAN);
    }

    public long winningBid() {
        return CodecUtil.int64Get(buffer, offset + 12, java.nio.ByteOrder.LITTLE_ENDIAN);
    }

    public int quantityLeft() {
        return CodecUtil.int32Get(buffer, offset + 20, java.nio.ByteOrder.LITTLE_ENDIAN);
    }

}
