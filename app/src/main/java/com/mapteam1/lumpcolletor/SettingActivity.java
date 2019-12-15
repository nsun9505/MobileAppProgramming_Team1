package com.mapteam1.lumpcolletor;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.Switch;


public class SettingActivity extends Activity {
    private static final String PREF_KEY = "gameSetting";
    private SeekBar seekBarSensitivity;
    private Switch toggleButtonSound;
    static int sensitivity;
    static boolean sound;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        LoadGameSetting(this);

        seekBarSensitivity = (SeekBar)this.findViewById(R.id.seekBar_sound);
        seekBarSensitivity.setProgress(sensitivity);

        toggleButtonSound = (Switch)this.findViewById(R.id.switch_sound);
        toggleButtonSound.setChecked(sound);
    }

    public static void LoadGameSetting(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        if(sharedPreferences!=null){
            sensitivity = sharedPreferences.getInt("sensitivity",20);
            sound = sharedPreferences.getBoolean("toggleSound", true);
        }
    }

    public void doSave(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREF_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        sensitivity = this.seekBarSensitivity.getProgress();
        editor.putInt("sensitivity", sensitivity);
        sound = this.toggleButtonSound.isChecked();
        editor.putBoolean("toggleSound", sound);
        editor.apply();

        Toast.makeText(this,"Setting Saved!",Toast.LENGTH_LONG).show();

        finish();
    }

    static public int getSensitivity() {
        return sensitivity;
    }
}
