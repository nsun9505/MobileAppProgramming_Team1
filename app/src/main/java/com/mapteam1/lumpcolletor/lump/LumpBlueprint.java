package com.mapteam1.lumpcolletor.lump;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;


public class LumpBlueprint {
    ArrayList<PartsData> layer = new ArrayList<>();

    public Bitmap Produce() {
        Bitmap result = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
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
            matrix.postRotate(data.angle);
            matrix.postScale(data.hflip, 1);
            matrix.postTranslate(128+data.xpos, 128+data.ypos);
            canvas.drawBitmap(parts_spr, matrix, paint);
        }
        return result;
    }
}

