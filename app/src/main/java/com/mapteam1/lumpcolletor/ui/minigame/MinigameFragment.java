package com.mapteam1.lumpcolletor.ui.minigame;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcolletor.R;

public class MinigameFragment extends Fragment {

    private MinigameViewModel minigameViewModel;

    GyroSystem gs;

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