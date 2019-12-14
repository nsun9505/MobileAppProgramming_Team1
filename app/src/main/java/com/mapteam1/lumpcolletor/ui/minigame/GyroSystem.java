package com.mapteam1.lumpcolletor.ui.minigame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.SettingFragment;
import com.mapteam1.lumpcolletor.function.Player;
import com.mapteam1.lumpcolletor.gameskin.GameParent;

public class GyroSystem {
    double roll;
    double pitch;

    private double rad_to_dgr = 180/ Math.PI;
    private static final float NS2S = 1.0f/1000000000.0f;

    double currY;
    double currX;

    double targetX;
    double targetY;

    int gyroSensitivity = 45 - SettingFragment.getSeekBarSound();

    int height;
    int width;

    private SensorManager mySensorManager;
    private SensorEventListener gyroListener;
    private Sensor myGyro;

    private double timestamp = 0.0;
    private double dt;



    GameParent game;

    public GyroSystem(final Activity a, final View root, final MinigameViewModel aa) {
        mySensorManager = (SensorManager) a.getSystemService(Context.SENSOR_SERVICE);
        myGyro = mySensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        game = GameParent.RandomGame();

        targetX = getTargetX();
        targetY = getTargetY();




        gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                double gyroY = event.values[0];
                double gyroX = event.values[1];


                dt = (event.timestamp - timestamp) * NS2S;
                timestamp = event.timestamp;

                height = root.getHeight();
                width = root.getWidth();

                game.Update((float)currX,(float)currY,(float)targetX,(float)targetY);
                gyroSensitivity = 45 - SettingFragment.getSeekBarSound();

                if (dt - timestamp * NS2S != 0) {


                    currX = getCurrX(gyroX,dt,width);
                    currY = getCurrY(gyroY,dt,height);



                    if(Math.sqrt(Math.pow(currX-targetX,2)+Math.pow(currY-targetY,2))<0.05){
                        Toast.makeText(a,"CLEAR!!",Toast.LENGTH_SHORT).show();

                        Player.getPlayer().increaseExp(10);
                        Player.getPlayer().updateMoney();
                        Player.getPlayer().increaseSearchValue(10);

                        game = GameParent.RandomGame();

                        targetX = getTargetX();
                        targetY = getTargetY();

                    }

                    Log.d("xyxy", "X: "+currX+"  Y:"+currY);

                    game.Update((float)currX,(float)currY,(float)targetX,(float)targetY);
                    aa.setBitmap(game.GetBitmap());

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void gyroOnResume() {
        mySensorManager.registerListener(gyroListener,myGyro,SensorManager.SENSOR_DELAY_GAME);
    }

    public void gyroOnPause() {
        mySensorManager.unregisterListener(gyroListener);
    }

    public double getTargetX() {
        this.targetX = Math.random();

        return targetX;
    }

    public double getTargetY() {
        this.targetY = Math.random();

        return targetY;
    }


    public double getCurrX(double gyroX,double dt,int width) {
        roll = roll + gyroX * dt;

        currX = roll*rad_to_dgr;

        if(currX>gyroSensitivity)
            currX=gyroSensitivity;
        if(currX<-gyroSensitivity)
            currX=-gyroSensitivity;

        currX = (currX+gyroSensitivity)/(gyroSensitivity*2);

        return currX;

    }

    public double getCurrY(double gyroY, double dt,int height) {
        pitch = pitch + gyroY * dt;

        currY = pitch *rad_to_dgr;

        if(currY>gyroSensitivity)
            currY=gyroSensitivity;
        if(currY<-gyroSensitivity)
            currY=-gyroSensitivity;

        currY = (currY+gyroSensitivity)/(gyroSensitivity*2);

        return currY;
    }
}
