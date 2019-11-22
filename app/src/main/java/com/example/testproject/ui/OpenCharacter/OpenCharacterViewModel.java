package com.example.testproject.ui.OpenCharacter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testproject.R;

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