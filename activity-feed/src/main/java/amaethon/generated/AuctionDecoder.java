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

public class AuctionDecoder {
    public static final int BLOCK_LENGTH = 16;
    public static final int TEMPLATE_ID = 1;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 0;

    private final AuctionDecoder parentMessage = this;
    protected int offset;
    protected int limit;
    protected int actingBlockLength;
    protected int actingVersion;
    private DirectBuffer buffer;

    public static int durationInNanosId() {
        return 1;
    }

    public static String durationInNanosMetaAttribute(final MetaAttribute metaAttribute) {
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

    public static long durationInNanosNullValue() {
        return -9223372036854775808L;
    }

    public static long durationInNanosMinValue() {
        return -9223372036854775807L;
    }

    public static long durationInNanosMaxValue() {
        return 9223372036854775807L;
    }

    public static int reserveId() {
        return 2;
    }

    public static String reserveMetaAttribute(final MetaAttribute metaAttribute) {
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

    public static long reserveNullValue() {
        return -9223372036854775808L;
    }

    public static long reserveMinValue() {
        return -9223372036854775807L;
    }

    public static long reserveMaxValue() {
        return 9223372036854775807L;
    }

    public static int nameId() {
        return 3;
    }

    public static String nameCharacterEncoding() {
        return "UTF-8";
    }

    public static String nameMetaAttribute(final MetaAttribute metaAttribute) {
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

    public static int nameHeaderSize() {
        return 1;
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

    public AuctionDecoder wrap(
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

    public long durationInNanos() {
        return CodecUtil.int64Get(buffer, offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
    }

    public long reserve() {
        return CodecUtil.int64Get(buffer, offset + 8, java.nio.ByteOrder.LITTLE_ENDIAN);
    }

    public int nameLength() {
        final int sizeOfLengthField = 1;
        final int limit = limit();
        buffer.checkLimit(limit + sizeOfLengthField);

        return CodecUtil.uint8Get(buffer, limit);
    }

    public int getName(final uk.co.real_logic.agrona.MutableDirectBuffer dst, final int dstOffset, final int length) {
        final int sizeOfLengthField = 1;
        final int limit = limit();
        buffer.checkLimit(limit + sizeOfLengthField);
        final int dataLength = CodecUtil.uint8Get(buffer, limit);
        final int bytesCopied = Math.min(length, dataLength);
        limit(limit + sizeOfLengthField + dataLength);
        buffer.getBytes(limit + sizeOfLengthField, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public int getName(final byte[] dst, final int dstOffset, final int length) {
        final int sizeOfLengthField = 1;
        final int limit = limit();
        buffer.checkLimit(limit + sizeOfLengthField);
        final int dataLength = CodecUtil.uint8Get(buffer, limit);
        final int bytesCopied = Math.min(length, dataLength);
        limit(limit + sizeOfLengthField + dataLength);
        buffer.getBytes(limit + sizeOfLengthField, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public String name() {
        final int sizeOfLengthField = 1;
        final int limit = limit();
        buffer.checkLimit(limit + sizeOfLengthField);
        final int dataLength = CodecUtil.uint8Get(buffer, limit);
        limit(limit + sizeOfLengthField + dataLength);
        final byte[] tmp = new byte[dataLength];
        buffer.getBytes(limit + sizeOfLengthField, tmp, 0, dataLength);

        final String value;
        try {
            value = new String(tmp, "UTF-8");
        } catch (final java.io.UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        return value;
    }
}
