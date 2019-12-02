package com.mapteam1.lumpcolletor.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.lump.LumpGenerator;

public class ShowLumpDetail extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lumpinfo);

        int index = getIntent().getIntExtra("index", -1);
        ImageView charview = findViewById(R.id.char_view);
        if (index >= 0) charview.setImageBitmap(LumpGenerator.getref()._test_imgs.get(index));
        Button button_close = findViewById(R.id.button_close); //설정버튼
        button_close.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mOnClose(v);
            }
        });
    }

    public void mOnClose(View v) {
        finish();
    }
}

