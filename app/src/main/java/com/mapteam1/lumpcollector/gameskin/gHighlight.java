package com.mapteam1.lumpcollector.gameskin;

import android.graphics.Bitmap;

import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Shader;

public class gHighlight extends GameParent {
    private Bitmap bg;

    private void createBg(float xtarget, float ytarget) {
        bg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        int grayCol;
        for(float i = 1; i > 0; i -= 0.1f) {
            grayCol = (int)(255 * (1-i));
            paint.setColor(Color.rgb(grayCol, grayCol, grayCol));
            canvas.drawCircle(xtarget, ytarget, i * width, paint);
        }
        paint.setColor(Color.RED);
        canvas.drawCircle(xtarget, ytarget, 5, paint);
    }

    @Override
    public void Update(float xpos, float ypos, float xtarget, float ytarget) {
        Canvas canvas = new Canvas(GameScreen);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawColor(Color.BLACK);

        paint.setColor(Color.WHITE);
        if (bg == null) createBg(xtarget*width, ytarget*height);

        paint.setMaskFilter(new MaskFilter());
        Shader shader = new BitmapShader(bg, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(shader);
        canvas.drawCircle(xpos*width, ypos*height, 30f, paint);

    }
}