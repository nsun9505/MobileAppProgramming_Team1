package com.mapteam1.lumpcollector.gameskin;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;

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
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawColor(Color.WHITE);

        float halfwidth = width + 0.5f;
        float halfheight = height + 0.5f;

        canvas.drawCircle(xpos + halfwidth, ypos+halfheight, 30f, paint);
        canvas.drawCircle(xtarget + halfwidth, ytarget+halfheight, 20f, paint);
    }

    public static GameParent RandomGame() {
        GameParent randomgame;
        int randomindex = new Random().nextInt(4);
        switch(randomindex) {
            case 0:
                randomgame = new gDowsing();
                break;
            case 1:
                randomgame = new gHighlight();
                break;
            case 2:
                randomgame = new gShapeSync();
                break;
            case 3:
                randomgame = new gWaveSync();
                break;
            default:
                randomgame = new GameParent();
                break;
        }
        return randomgame;
    }
}

