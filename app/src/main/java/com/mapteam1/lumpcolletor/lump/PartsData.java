package com.mapteam1.lumpcolletor.lump;

public class PartsData {
    String key;
    int index;
    int xpos;
    int ypos;
    int size;
    int angle;
    int hflip;

    public PartsData() {}

    public PartsData(PartsData c) {
        key = c.key;
        index = c.index;
        xpos = c.xpos;
        ypos = c.ypos;
        size = c.size;
        angle = c.angle;
        hflip = c.hflip;
    }
}