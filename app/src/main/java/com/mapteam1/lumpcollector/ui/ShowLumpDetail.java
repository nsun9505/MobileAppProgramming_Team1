package com.mapteam1.lumpcollector.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapteam1.lumpcollector.R;
import com.mapteam1.lumpcollector.function.Player;
import com.mapteam1.lumpcollector.lump.Lump;

public class ShowLumpDetail extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lumpinfo);

        final int index = getIntent().getIntExtra("index", -1);
        ImageView charview = findViewById(R.id.char_view);
        TextView charinfo = findViewById(R.id.char_info);
        final Lump charLump;
        if (index >= 0) {
            charLump = Player.getPlayer().getLumpList().get(index);
            charview.setImageBitmap(charLump.getBitmap());
            charinfo.setText(charLump.GetSkillDescription());
        } else {
            charLump = null;
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
                if (Player.getPlayer().isLumpActive(charLump)) {
                    button_release.setText("비활성화해서 놓아주기");
                    repeat = 3;
                } else if (repeat > 0) {
                    button_release.setText(repeat + "회 더 눌러서 놓아주기");
                    repeat--;
                } else {
                    Player.getPlayer().getLumpList().remove(index);
                    mOnClose(v);
                }
            }
        });

        final Button button_toggle_active = findViewById(R.id.button_toggle_active);
        final Player player = Player.getPlayer();
        final String activate = "스킬 활성화 (%d/%d)";
        final String deactivate = "스킬 비활성화 (%d/%d)";
        final String fullyactivated = "더이상 활성화할 수 없음 (%d/%d)";
        if (player.isLumpActive(charLump)) {
            button_toggle_active.setText(String.format(deactivate, player.getActiveLumpCount(), player.getMaxActiveLump()));
        } else {
            button_toggle_active.setText(String.format(activate, player.getActiveLumpCount(), player.getMaxActiveLump()));
        }
        button_toggle_active.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (player.isLumpActive(charLump)) {
                    if (player.setLumpInactive(charLump))
                        button_toggle_active.setText(String.format(activate, player.getActiveLumpCount(), player.getMaxActiveLump()));
                } else {
                    if (player.setLumpActive(charLump))
                        button_toggle_active.setText(String.format(deactivate, player.getActiveLumpCount(), player.getMaxActiveLump()));
                    else
                        button_toggle_active.setText(String.format(fullyactivated, player.getActiveLumpCount(), player.getMaxActiveLump()));
                }
            }
        });
    }

    public void mOnClose(View v) {
        finish();
    }
}

