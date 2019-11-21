package com.example.testproject.ui.openitem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OpenItemViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OpenItemViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is start fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}