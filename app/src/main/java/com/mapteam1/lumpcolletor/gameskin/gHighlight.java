package com.mapteam1.lumpcolletor.gameskin;

import android.graphics.Bitmap;

import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class gHighlight extends GameParent {
    private Bitmap bg;

    public gHighlight() {
        super();
    }

    private void createBg(float xtarget, float ytarget) {

        bg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        int grayCol;
        for(float i = 1; i > 0; i -= 0.2f) {
            grayCol = (int)(255 * (1-i));
            paint.setColor(Color.rgb(grayCol, grayCol, grayCol));
            canvas.drawCircle(xtarget, ytarget, i * width, paint);
        }
    }

    @Override
    public void Update(float xpos, float ypos, float xtarget, float ytarget) {
        Canvas canvas = new Canvas(GameScreen);
        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, width, height, paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(xpos*width, ypos*height, 30f, paint);
        canvas.drawCircle(xtarget*width, ytarget*height, 20f, paint);
    }
}