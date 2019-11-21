package com.example.testproject.ui.OpenCharacter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OpenCharacterViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OpenCharacterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is OpenCharacter fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}