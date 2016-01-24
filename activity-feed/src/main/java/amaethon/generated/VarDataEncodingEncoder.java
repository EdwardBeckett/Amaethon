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

import uk.co.real_logic.agrona.MutableDirectBuffer;
import uk.co.real_logic.sbe.codec.java.CodecUtil;

public class VarDataEncodingEncoder {
    private MutableDirectBuffer buffer;
    private int offset;
    private int actingVersion;

    public static short lengthNullValue() {
        return (short) 255;
    }

    public static short lengthMinValue() {
        return (short) 0;
    }

    public static short lengthMaxValue() {
        return (short) 254;
    }

    public static short varDataNullValue() {
        return (short) 255;
    }

    public static short varDataMinValue() {
        return (short) 0;
    }

    public static short varDataMaxValue() {
        return (short) 254;
    }

    public VarDataEncodingEncoder wrap(final MutableDirectBuffer buffer, final int offset, final int actingVersion) {
        this.buffer = buffer;
        this.offset = offset;
        this.actingVersion = actingVersion;
        return this;
    }

    public int size() {
        return -1;
    }

    public VarDataEncodingEncoder length(final short value) {
        CodecUtil.uint8Put(buffer, offset + 0, value);
        return this;
    }
}
