package com.mapteam1.lumpcolletor.ui.minigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcolletor.MainActivity;
import com.mapteam1.lumpcolletor.R;

import com.mapteam1.lumpcolletor.gameskin.GameParent;


import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class MinigameFragment extends Fragment {

    private MinigameViewModel minigameViewModel;


    GyroSystem gs;

    int height;
    int width;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        minigameViewModel =
                ViewModelProviders.of(this).get(MinigameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_minigame, container, false);


        final ImageView imageview = root.findViewById(R.id.MinigameImage);


        gs = new GyroSystem(getActivity(),root,minigameViewModel);


        minigameViewModel.getBitmap().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(@Nullable Bitmap s) {
                imageview.setImageBitmap(s);
            }
        });

        return root;
    }


    public void onResume() {
        super.onResume();
        gs.gyroOnResume();

    }

    public void onPause() {
        super.onPause();

        gs.gyroOnPause();

    }
    public void onStop() {
        super.onStop();
    }

}