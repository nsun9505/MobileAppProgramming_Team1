package com.mapteam1.lumpcolletor.gameskin;

import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Matrix;

import com.mapteam1.lumpcolletor.function.Player;
import com.mapteam1.lumpcolletor.lump.Lump;
import com.mapteam1.lumpcolletor.lump.LumpGenerator;

import java.util.ArrayList;
import java.util.Random;

public class gShapeSync extends GameParent {
    private Bitmap RandomShape = null;

    public gShapeSync() {
        super();

        Random random = new Random();
        if (random.nextBoolean()) {
            ArrayList<Lump> lumpList = Player.getPlayer().getLumpList();
            if (lumpList.size() > 1) {
                RandomShape = lumpList.get(random.nextInt(lumpList.size())).getBitmap();
            }
        }
        if (RandomShape == null) {
            String parts_key = "BODY";
            int parts_index = LumpGenerator.getref().getPartsRandomIndex(parts_key);
            RandomShape = LumpGenerator.getref().getPartsBitmap("BODY", parts_index);
        }
    }

    public Matrix presetMat(float factor1, float factor2, int shapeWidth, int shapeHeight) {
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f + factor1, 0.5f + factor2, shapeWidth/2, shapeHeight/2);
        matrix.postTranslate((width-shapeWidth)/2, (height-shapeHeight)/2);
        return matrix;
    }

    @Override
    public void Update(float xpos, float ypos, float xtarget, float ytarget) {
        Canvas canvas = new Canvas(GameScreen);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawColor(Color.WHITE);

        Matrix preset;
        int shapeWidth = RandomShape.getWidth();
        int shapeHeight = RandomShape.getHeight();

        paint.setColorFilter(null);
        preset = presetMat(xpos, ypos, shapeWidth, shapeHeight);
        canvas.drawBitmap(RandomShape, preset, paint);

        paint.setAlpha(128);
        paint.setColorFilter(new LightingColorFilter(255, 255));
        preset = presetMat(xtarget, ytarget, shapeWidth, shapeHeight);
        canvas.drawBitmap(RandomShape, preset, paint);
    }
}