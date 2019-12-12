package com.mapteam1.lumpcolletor.ui.lootlump;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mapteam1.lumpcolletor.MainActivity;
import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.function.GameInterface;
import com.mapteam1.lumpcolletor.function.Player;
import com.mapteam1.lumpcolletor.lump.Lump;
import com.mapteam1.lumpcolletor.lump.LumpBlueprint;
import com.mapteam1.lumpcolletor.lump.LumpGenerator;

public class LootLumpViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //private MutableLiveData<LumpBlueprint> mLumpBlueprint;
    private MutableLiveData<Bitmap> mBitmap;

    public LootLumpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(LumpGenerator.getref()._test_error_msg);
        //mLumpBlueprint = new MutableLiveData<>();
        //mLumpBlueprint.setValue(LumpGenerator.getref().MakeBlueprint());
        mBitmap = new MutableLiveData<>();
        //changeLump();//mBitmap.setValue(LumpGenerator.getref().MakeBlueprint().Produce());
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<Bitmap> getBitmap() {return mBitmap;}
    //public LiveData<LumpBlueprint> getBlueprint() {return mLumpBlueprint;}

    public void changeLump() {
        mBitmap.setValue(LumpGenerator.getref().MakeBlueprint().Produce());
    }

    public void createLump() {
        Lump lump = new Lump(LumpGenerator.getref().MakeBlueprint());
        Player.getPlayer().getLumpList().add(lump);
        mBitmap.setValue(lump.getBitmap());
    }
}