package com.mapteam1.lumpcolletor.ui.lootlump;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mapteam1.lumpcolletor.function.Player;
import com.mapteam1.lumpcolletor.lump.Lump;
import com.mapteam1.lumpcolletor.lump.LumpGenerator;

public class LootLumpViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Bitmap> mBitmap;

    public LootLumpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(LumpGenerator.getref()._test_error_msg);
        mBitmap = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<Bitmap> getBitmap() {return mBitmap;}

    public void createLump() {
        Lump lump = new Lump(LumpGenerator.getref().MakeBlueprint());
        Player.getPlayer().getLumpList().add(lump);
        mBitmap.setValue(lump.getBitmap());
    }
}