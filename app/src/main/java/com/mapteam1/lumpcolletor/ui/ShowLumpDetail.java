package com.mapteam1.lumpcolletor.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.function.GameInterface;
import com.mapteam1.lumpcolletor.lump.LumpGenerator;

import java.util.ArrayList;

public class ShowLumpDetail extends AppCompatActivity {
    private View decorView;
    private int uiOption;
 //   public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar(); //강제로 타이틀바 숨김
        actionBar.hide(); //타이틀바 숨김 못쓰는이유 : 컨테이너에서 위로 안올라감
        //전체화면시작
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        //전체화면 중간 스톱

        setContentView(R.layout.char_info);

        ImageView charview = findViewById(R.id.char_view);

  //      TextView tv = findViewById(R.id.char_info);
 //       Button btn = findViewById(R.id.button_close);


    }

}

