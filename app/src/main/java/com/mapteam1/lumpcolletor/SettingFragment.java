package com.mapteam1.lumpcolletor;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingFragment extends Activity {
    private SeekBar seekBarSound;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        this.seekBarSound = (SeekBar)this.findViewById(R.id.seekBar_sound);
        this.loadGameSetting();
    }

    private void loadGameSetting(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
        if(sharedPreferences!=null){
            int sound = sharedPreferences.getInt("sound",95);
            this.seekBarSound.setProgress(sound);
        }
        else{
            Toast.makeText(this,"USE the default setting",Toast.LENGTH_LONG).show();
        }
    }

    public void doSave(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences("gameSetting",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("sound",this.seekBarSound.getProgress());

        editor.apply();

        Toast.makeText(this,"Setting Saved!",Toast.LENGTH_LONG).show();

        finish();
    }
}
