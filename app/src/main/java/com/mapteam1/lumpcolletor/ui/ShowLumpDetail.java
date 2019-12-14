package com.mapteam1.lumpcolletor.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.function.Player;
import com.mapteam1.lumpcolletor.lump.Lump;

public class ShowLumpDetail extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lumpinfo);

        final int index = getIntent().getIntExtra("index", -1);
        ImageView charview = findViewById(R.id.char_view);
        TextView charinfo = findViewById(R.id.char_info);
        if (index >= 0) {
            Lump charLump = Player.getPlayer().getLumpList().get(index);
            charview.setImageBitmap(charLump.getBitmap());
            charinfo.setText(charLump.GetSkillDescription());
        }
        Button button_close = findViewById(R.id.button_close); //설정버튼
        button_close.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mOnClose(v);
            }
        });

        final Button button_release = findViewById(R.id.button_release);
        button_release.setOnClickListener(new View.OnClickListener(){
            int repeat = 3;

            public void onClick(View v) {
                if (repeat > 0) {
                    button_release.setText(repeat+"회 더 눌러서 놓아주기");
                    repeat--;
                } else {
                    Player.getPlayer().getLumpList().remove(index);
                    mOnClose(v);
                }
            }
        });
    }

    public void mOnClose(View v) {
        finish();
    }
}

