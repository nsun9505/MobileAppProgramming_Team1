package com.mapteam1.lumpcollector.gameskin;

import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class movePoint extends GameParent {
    protected Bitmap GameScreen;
    protected int width;
    protected int height;

    public movePoint() {
        width = 320;
        height = 320;
        GameScreen = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    public Bitmap GetBitmap() {
        return GameScreen;
    }

    public void Update(float xpos, float ypos, float xtarget, float ytarget) {
        Canvas canvas = new Canvas(GameScreen);
        Paint paint = new Paint();

        //Log.d("135135", "X: "+xpos+"  Y:"+ypos);
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, width, height, paint);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(xpos*width, ypos*height, 30f, paint);
        canvas.drawCircle(xtarget*width, ytarget*height, 20f, paint);
    }
}