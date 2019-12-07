package com.mapteam1.lumpcolletor.gameskin;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.mapteam1.lumpcolletor.lump.LumpGenerator;
import com.mapteam1.lumpcolletor.lump.PartsData;

public class GameParent {
    protected Bitmap GameScreen;
    protected float width;
    protected float height;

    public GameParent() {
        GameScreen = Bitmap.createBitmap(320, 320, Bitmap.Config.ARGB_8888);
    }

    public Bitmap GetBitmap() {
        return GameScreen;
    }

    public void Update(float xpos, float ypos, float xtarget, float ytarget) {
        Canvas canvas = new Canvas(GameScreen);
        Paint paint = new Paint();
        float halfwidth = width + 0.5f;
        float halfheight = height + 0.5f;

        canvas.drawCircle(xpos + halfwidth, ypos+halfheight, 30f, paint);
        canvas.drawCircle(xtarget + halfwidth, ytarget+halfheight, 20f, paint);
    }
}

