package com.mapteam1.lumpcollector.ui.lootbox;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LootBoxViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LootBoxViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is start fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}