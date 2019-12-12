package com.mapteam1.lumpcolletor.lump;

import android.graphics.Bitmap;

public class Lump {
    LumpBlueprint lbp;
    Bitmap imgCache;

    public Lump(LumpBlueprint lbp) {
        this.lbp = lbp;
    }

    public Lump(String data) {
        lbp = new LumpBlueprint();
        lbp.Decode(data);
    }

    public Bitmap getBitmap() {
        if (lbp == null) return null;
        if (imgCache == null)
            imgCache = lbp.Produce();
        return imgCache;
    }


    public String Encode() {
        if (lbp == null) return null;
        return lbp.Encode();
    }
}
