package com.mapteam1.lumpcolletor.ui.showlump;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShowLumpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShowLumpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is showcharacter fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}