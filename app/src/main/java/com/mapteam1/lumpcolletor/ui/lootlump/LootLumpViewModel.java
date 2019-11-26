package com.mapteam1.lumpcolletor.ui.lootlump;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LootLumpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LootLumpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is LootLump fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}