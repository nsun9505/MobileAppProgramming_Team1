package com.mapteam1.lumpcolletor;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Window;
import android.view.WindowManager;

import com.mapteam1.lumpcolletor.function.GameInterface;
import com.mapteam1.lumpcolletor.function.Player;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mapteam1.lumpcolletor.function.SaveData;
import com.mapteam1.lumpcolletor.function.WorkThread;
import com.mapteam1.lumpcolletor.lump.LumpGenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    private Thread updateThread;
    private View decorView;
    private int uiOption;
    private GameInterface Ginterface = null;
    private SaveData saveData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
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
        setContentView(R.layout.activity_main);

        Button settingbtn = (Button) findViewById(R.id.settingbtn); //설정버튼
        settingbtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) { //클릭이벤트
                Intent myintent = new Intent(MainActivity.this, SettingFragment.class);
                startActivity(myintent);
            }
        });

        this.initGameInfo();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_gamestart, R.id.navigation_open_itembox,
                R.id.navigation_open_charbox,R.id.navigation_view_character,R.id.navigation_upgrade)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //덩어리생성기 선언
        LumpGenerator.getref().setRes(getResources());
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) { //전체화면2
        // TODO Auto-generated method stub
        // super.onWindowFocusChanged(hasFocus);

        if( hasFocus ) {
            decorView.setSystemUiVisibility( uiOption );
        }
    } //전체화면끝

    public void initGameInfo(){
        this.Ginterface = Player.getPlayer();
        saveData = new SaveData(this);
        Player.getPlayer().Load(saveData);

        final TextView levelText = (TextView)findViewById(R.id.textView3);
        levelText.setText(String.valueOf(Ginterface.getCurrentLevel() +" LV"));

        int cur, max;
        cur = Ginterface.getCurrentExp();
        max = Ginterface.getMaxExp();
        final TextView expText = (TextView)findViewById(R.id.exp_value);
        expText.setText(String.format("%d/%d", cur, max));
        final ProgressBar expProgress = (ProgressBar)findViewById(R.id.progressBar);
        expProgress.setProgress((cur * 100)/max);
        expProgress.setMax(max);

        cur = Ginterface.getSearchValue();
        max = Ginterface.getMaxSearchValue();
        final TextView searchText = (TextView)findViewById(R.id.search_value);
        searchText.setText(String.format("%d/%d", cur, max));
        final ProgressBar searchProgress = (ProgressBar)findViewById(R.id.progressBar2);
        searchProgress.setProgress((cur * 100)/max);

        final TextView moneyText = (TextView)findViewById(R.id.textView4);
        moneyText.setText(String.valueOf(Ginterface.getMoney()));

        final TextView boxText = (TextView)findViewById(R.id.textView5);
        boxText.setText(String.valueOf(Ginterface.getNumOfBox()));

        Handler handler = new Handler(){
          @Override
          public void handleMessage(Message msg){
            switch(msg.what){
                case WorkThread.UPDATE_LEVEL:
                    levelText.setText(msg.obj.toString());
                    break;
                case WorkThread.UPDATE_MONEY:
                    moneyText.setText(msg.obj.toString());
                    break;
                case WorkThread.UPDATE_EXP:
                    expProgress.setProgress(msg.arg1);
                    expText.setText(msg.obj.toString());
                    break;
                case WorkThread.UPDATE_SEARCHVALUE:
                    searchProgress.setProgress(msg.arg1);
                    searchText.setText(msg.obj.toString());
                    break;
                case WorkThread.UPDATE_MAX_SEARCH_VALUE:
                    searchText.setText(msg.obj.toString());
                    searchProgress.setMax(Player.getPlayer().getMaxSearchValue());
                    searchProgress.setProgress(Player.getPlayer().getSearchValue());
                    break;
                case WorkThread.UPDATE_NUM_OF_BOX:
                    boxText.setText(msg.obj.toString());
            }
          }
        };

        updateThread = new WorkThread(handler);
        updateThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Player.getPlayer().Save(saveData);
    }
}
