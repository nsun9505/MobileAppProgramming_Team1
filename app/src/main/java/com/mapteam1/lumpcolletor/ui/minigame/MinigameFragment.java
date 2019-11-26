package com.mapteam1.lumpcolletor.ui.minigame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcolletor.R;

public class MinigameFragment extends Fragment {

    private MinigameViewModel minigameViewModel;

    private SensorManager mySensorManager;
    private SensorEventListener gyroListener;
    private Sensor myGyro;

    double roll;
    double pitch;
    double yaw;

    private double timestamp = 0.0;
    private double dt;

    private double rad_to_dgr = 180/ Math.PI;
    private static final float NS2S = 1.0f/1000000000.0f;


    movePoint mv;

//    Button btn1;

    int width;
    int height;

    double dgrpitch;
    double dgrroll;

    double anspitch = Math.random()*120 - 60;
    double ansroll = Math.random()*120 -60;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        minigameViewModel =
                ViewModelProviders.of(this).get(MinigameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_minigame, container, false);


        mv = (movePoint)root.findViewById(R.id.mp);

//        btn1=(Button)root.findViewById(R.id.btn1);

        mySensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        myGyro = mySensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

/*        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.initialize(height,width);
                pitch = 0;
                roll = 0;
                yaw = 0;
            }
        });
*/


        mv.initialize(height,width);
        Log.d("ans", "X: "+anspitch);
        Log.d("ans", "Y: "+ansroll);
        gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                double gyroX = event.values[0];
                double gyroY = event.values[1];
                double gyroZ = event.values[2];

                width = mv.getWidth();
                height = mv.getHeight();

                dt = (event.timestamp - timestamp) * NS2S;
                timestamp = event.timestamp;

                if (dt - timestamp * NS2S != 0) {
                    pitch = pitch + gyroY * dt;
                    roll = roll + gyroX * dt;
                    yaw = yaw + gyroZ * dt;

                    dgrpitch = pitch *rad_to_dgr;
                    dgrroll = roll*rad_to_dgr;

                    if(dgrpitch>60)
                        dgrpitch=60;
                    if(dgrpitch<-60)
                        dgrpitch=-60;
                    if(dgrroll>60)
                        dgrroll=60;
                    if(dgrroll<-60)
                        dgrroll=-60;

                    if((dgrpitch>anspitch-2)&&(dgrpitch<anspitch+2)&&(dgrroll>ansroll-2)&&(dgrroll<ansroll+2)){
                        Toast.makeText(getActivity(),"CLEAR!!",Toast.LENGTH_SHORT).show();

                    }

                    Log.d("xyxy", "X: "+dgrpitch+"  Y:"+dgrroll);
                    mv.setX((float)dgrpitch,width);
                    mv.setY((float)dgrroll,height);
                    mv.invalidate();

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        return root;
    }


    public void onResume() {
        super.onResume();
        mySensorManager.registerListener(gyroListener,myGyro,SensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause() {
        super.onPause();
        mySensorManager.unregisterListener(gyroListener);
    }
    public void onStop() {
        super.onStop();
    }

}