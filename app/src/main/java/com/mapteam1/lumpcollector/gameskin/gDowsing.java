package com.mapteam1.lumpcollector.gameskin;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.android.material.math.MathUtils;

public class gDowsing extends GameParent {
    int loop = 0;
    long now = 0;
    long last = 0;

    @Override
    public void Update(float xpos, float ypos, float xtarget, float ytarget) {
        Canvas canvas = new Canvas(GameScreen);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawColor(Color.WHITE);

        float length = MathUtils.dist(xpos, ypos, xtarget, ytarget) * width;

        BlurMaskFilter filter = new BlurMaskFilter(10, BlurMaskFilter.Blur.OUTER);
        paint.setColor(Color.MAGENTA);
        canvas.drawCircle(xpos*width, ypos*height, 10f, paint);

        paint.setMaskFilter(filter);
        if (length < 200) {
            canvas.drawCircle(xpos * width, ypos * height, loop, paint);
        }
        if (length < 150) {
            canvas.drawCircle(xpos * width, ypos * height, (loop+150) % 200, paint);
        }
        if (length < 100) {
            canvas.drawCircle(xpos * width, ypos * height, (loop+100) % 200, paint);
        }
        if (length < 50) {
            canvas.drawCircle(xpos * width, ypos * height, (loop+50) % 200, paint);

            paint.setColor(Color.GREEN);
            paint.setAlpha((int)(255 - length*5));
            paint.setMaskFilter(null);
            canvas.drawCircle(xtarget*width, ytarget*height, 10f, paint);
        }


        last = now;
        now = System.currentTimeMillis();
        int dt = Math.max(1, (int)(now - last)/10);
        loop = (loop+dt)%200;
    }
}