package com.mapteam1.lumpcolletor.ui.minigame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class movePoint extends View {
    float x = 500;
    float y = 640;

    public movePoint(Context context) {
        super(context);
    }

    public movePoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public movePoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setX(float pitch, int width){
        pitch+=60;

        this.x = (float)width/120*pitch;

    }

    public void setY(float roll,int height){
        roll+=60;

        this.y = (float)height/120*roll;
    }

    public void initialize(int height,int width){
        this.x = width/2;
        this.y = height/2;
    }
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Color.WHITE);


        canvas.drawCircle(x,y,50,paint);

        //Log.d("xyxy", "X: "+x+"Y:"+y);


    }
}
