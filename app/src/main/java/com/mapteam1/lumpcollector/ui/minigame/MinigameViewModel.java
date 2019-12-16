package com.mapteam1.lumpcollector.ui.minigame;


import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MinigameViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Bitmap> mBitmap;

    public MinigameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is start fragment");

        mBitmap = new MutableLiveData<>();
    }

    public void setBitmap(Bitmap img) {
        mBitmap.setValue(img);
    }

    public LiveData<Bitmap> getBitmap() {return mBitmap;}


    public LiveData<String> getText() {
        return mText;
    }
}