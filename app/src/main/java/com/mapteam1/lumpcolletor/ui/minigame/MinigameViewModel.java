package com.mapteam1.lumpcolletor.ui.minigame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MinigameViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MinigameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is start fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}