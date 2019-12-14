package com.mapteam1.lumpcolletor.gameskin;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import android.graphics.Matrix;
import android.graphics.Paint;

import com.mapteam1.lumpcolletor.lump.LumpGenerator;
import com.mapteam1.lumpcolletor.lump.PartsData;

import java.util.Random;


public class GameParent{
    protected Bitmap GameScreen;
    protected int width;
    protected int height;

    public GameParent() {
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

        float halfwidth = width + 0.5f;
        float halfheight = height + 0.5f;

        canvas.drawCircle(xpos + halfwidth, ypos+halfheight, 30f, paint);
        canvas.drawCircle(xtarget + halfwidth, ytarget+halfheight, 20f, paint);
    }

    public static GameParent RandomGame() {
        GameParent randomgame;
        int randomindex = new Random().nextInt(2);
        switch(randomindex) {
            case 0:
                randomgame = new movePoint();
                break;
            case 1:
                randomgame = new gameSecond();
                break;
            default:
                randomgame = new GameParent();
                break;
        }
        return randomgame;
    }
}

