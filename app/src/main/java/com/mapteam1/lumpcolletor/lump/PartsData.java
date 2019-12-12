package com.mapteam1.lumpcolletor.lump;

import android.util.Log;

import androidx.core.math.MathUtils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PartsData {
    String key;
    int index;
    int xpos;
    int ypos;
    int size;
    int angle;
    int hflip;

    public PartsData() {
        key = "BODY";
        index = 0;
        xpos = 0;
        ypos = 0;
        size = 0;
        angle = 0;
        hflip = 1;
    }

    public PartsData(PartsData c) {
        key = c.key;
        index = c.index;
        xpos = c.xpos;
        ypos = c.ypos;
        size = c.size;
        angle = c.angle;
        hflip = c.hflip;
    }

    public void _test_stringify() {
        String encoded = Encode();
        Log.d("before", key+" index"+index+" xpos"+xpos+" ypos"+ypos+" size"+size+" angle"+angle+" hflip"+hflip);
        Log.d("encoded", encoded);
        Decode(encoded);
        Log.d("after", key+" index"+index+" xpos"+xpos+" ypos"+ypos+" size"+size+" angle"+angle+" hflip"+hflip);
    }

    public String Encode() {
        int length = 7 * 4;
        ByteBuffer buffer;
        buffer = ByteBuffer.allocate(length);
        buffer.put(key.getBytes());

        index = limitThousand(index);
        xpos = limitThousand(xpos);
        ypos = limitThousand(ypos);
        size = limitThousand(size);
        angle = limitThousand(angle);
        hflip = limitThousand(hflip);

        buffer.put(IntTo4Bytes(index));
        buffer.put(IntTo4Bytes(xpos));
        buffer.put(IntTo4Bytes(ypos));
        buffer.put(IntTo4Bytes(size));
        buffer.put(IntTo4Bytes(angle));
        buffer.put(IntTo4Bytes(hflip));

        byte[] bytes = new byte[length];
        buffer.rewind();
        buffer.get(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }


    public void Decode(String data) {
        int length = 7 * 4;
        ByteBuffer buffer;
        buffer = ByteBuffer.allocate(length);
        buffer.put(data.getBytes(), 0, length);

        buffer.rewind();
        byte[] bytes = new byte[4];

        buffer.get(bytes);
        key = new String(bytes);
        index = parseBuffer(buffer, bytes);
        xpos = parseBuffer(buffer, bytes);
        ypos = parseBuffer(buffer, bytes);
        size = parseBuffer(buffer, bytes);
        angle = parseBuffer(buffer, bytes);
        hflip = parseBuffer(buffer, bytes);
    }

    private int parseBuffer(ByteBuffer buffer, byte[] bytes) {
        buffer.get(bytes);
        return Integer.parseInt(new String(bytes).trim());
    }

    private int limitThousand(int value) {
        return MathUtils.clamp(value, -999, 999);
    }

    private byte[] IntTo4Bytes(int value) {
        return String.format("%4d", value).getBytes();
    }
}