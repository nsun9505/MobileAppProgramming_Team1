package com.mapteam1.lumpcolletor.lump;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;


public class LumpBlueprint {
    private final static String REGEX = "/";
    ArrayList<PartsData> layer = new ArrayList<>();

    public Bitmap Produce() {
        Bitmap result = Bitmap.createBitmap(320, 320, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        int i;
        Matrix matrix = new Matrix();
        Paint paint = new Paint();

        PartsData data;

        Bitmap parts_spr;
        int _layer_num = layer.size();
        for(i = 0; i < _layer_num; i++) {
            data = layer.get(i);
            parts_spr = LumpGenerator.getref().getPartsBitmap(data.key, data.index);

            matrix.reset();
            matrix.preTranslate(-data.size, -data.size);
            matrix.postScale(data.hflip, 1);
            matrix.postRotate(-data.angle);
            matrix.postTranslate(160+data.xpos, 160+data.ypos);
            canvas.drawBitmap(parts_spr, matrix, paint);
        }
        return result;
    }

    public String Encode() {
        StringBuilder encoded = new StringBuilder();
        int i, layercnt = layer.size();
        for (i = 0; i < layercnt; i++) {
            if (i > 0) encoded.append(REGEX);
            encoded.append(layer.get(i).Encode());
        }
        return encoded.toString();
    }

    public void Decode(String data) {
        String[] layerStrList = data.split(REGEX);
        PartsData partsData;
        int i, layercnt = layerStrList.length;
        for (i = 0; i < layercnt; i++) {
            partsData = new PartsData();
            partsData.Decode(layerStrList[i]);
            layer.add(partsData);
        }
    }

    public void _test_stringify() {
        String encoded = Encode();
        layer.clear();
        Decode(encoded);
    }
}

