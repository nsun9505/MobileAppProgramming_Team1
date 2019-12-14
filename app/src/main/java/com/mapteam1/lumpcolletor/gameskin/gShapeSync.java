package com.mapteam1.lumpcolletor.gameskin;

import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Matrix;

import com.mapteam1.lumpcolletor.lump.LumpGenerator;

public class gShapeSync extends GameParent {
    private Bitmap RandomShape;

    public gShapeSync() {
        super();
        String parts_key = "BODY";
        int parts_index = LumpGenerator.getref().getPartsRandomIndex(parts_key);
        RandomShape = LumpGenerator.getref().getPartsBitmap("BODY", parts_index);
    }

    public Bitmap GetBitmap() {
        return GameScreen;
    }

    public void Update(float xpos, float ypos, float xtarget, float ytarget) {
        Canvas canvas = new Canvas(GameScreen);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Matrix preset;
        int shapeWidth = RandomShape.getWidth();
        int shapeHeight = RandomShape.getHeight();

        canvas.drawColor(Color.WHITE);
        paint.setColorFilter(null);
        preset = presetMat(xpos, ypos, shapeWidth, shapeHeight);
        canvas.drawBitmap(RandomShape, preset, paint);

        paint.setAlpha(128);
        paint.setColorFilter(new LightingColorFilter(255, 255));
        preset = presetMat(xtarget, ytarget, shapeWidth, shapeHeight);
        canvas.drawBitmap(RandomShape, preset, paint);
    }

    public Matrix presetMat(float factor1, float factor2, int shapeWidth, int shapeHeight) {
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f + factor1, 0.5f + factor2, shapeWidth/2, shapeHeight/2);
        matrix.postTranslate((width-shapeWidth)/2, (height-shapeHeight)/2);
        return matrix;
    }
}