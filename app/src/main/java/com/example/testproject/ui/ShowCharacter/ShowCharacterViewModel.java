package com.example.testproject.ui.ShowCharacter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShowCharacterViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShowCharacterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is showcharacter fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}