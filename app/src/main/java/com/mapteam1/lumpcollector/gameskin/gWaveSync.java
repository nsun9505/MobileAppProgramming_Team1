package com.mapteam1.lumpcollector.gameskin;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class gWaveSync  extends GameParent{
    float loop = 0;
    long now = 0;
    long last = 0;

    public gWaveSync() {
        super();
    }

    private void updatePath(Path path, float factor1, float factor2) {
        path.reset();
        int ycenter = height / 2;

        float wavelength = 2 + 6 * factor1;
        float amplitude = (0.5f + factor2) * height * 0.3f;

        path.moveTo(0, ycenter);
        float freq = -0.05f*(width/wavelength);
        for(float i = 0; i < width; i += wavelength) {
            freq += 0.1;
            path.lineTo(i, ycenter - (float)Math.sin(Math.PI*freq+loop) * amplitude);
        }
        path.lineTo(width, ycenter);
    }

    @Override
    public void Update(float xpos, float ypos, float xtarget, float ytarget) {
        Canvas canvas = new Canvas(GameScreen);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawColor(Color.BLACK);

        BlurMaskFilter filter = new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID);
        paint.setMaskFilter(filter);

        Path path = new Path();

        paint.setColor(Color.GREEN);
        paint.setAlpha(128);
        updatePath(path, xtarget, ytarget);
        canvas.drawPath(path, paint);

        paint.setColor(Color.WHITE);
        paint.setAlpha(128);
        updatePath(path, xpos, ypos);
        canvas.drawPath(path, paint);

        last = now;
        now = System.currentTimeMillis();
        int dt = Math.max(1, (int)(now - last)/10);
        loop = (loop+dt*0.02f)%(float)(Math.PI*2);
    }
}
