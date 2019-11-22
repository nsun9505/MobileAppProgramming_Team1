package com.example.testproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Window;
import android.view.WindowManager;

import com.example.testproject.function.GameInterface;
import com.example.testproject.function.Player;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    private View decorView;
    private int uiOption;
    private GameInterface Ginterface = null;
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
        setContentView(R.layout.activity_main);

        ImageButton settingbtn = (ImageButton) findViewById(R.id.settingbtn); //설정버튼
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
        this.Ginterface = new Player();
        TextView levelText = (TextView)findViewById(R.id.textView3);
        levelText.setText(String.valueOf(Ginterface.getCurrentLevel() +" LV"));

        ProgressBar expProgress = (ProgressBar)findViewById(R.id.progressBar);
        expProgress.setProgress(Ginterface.getCurrentExp());

        ProgressBar searchProgress = (ProgressBar)findViewById(R.id.progressBar2);
        searchProgress.setProgress(Ginterface.getSearchValue());

        TextView moneyText = (TextView)findViewById(R.id.textView4);
        moneyText.setText(String.valueOf(Ginterface.getMoney()));
    }

    public void gameClear(){
        int ret =  Ginterface.increaseExp(10);
        if(ret == 1){
            Ginterface.levelUp();
            Ginterface.increaseNumberOfBox();
            TextView levelText = (TextView)findViewById(R.id.textView3);
            levelText.setText(String.valueOf(Ginterface.getCurrentLevel() +" LV"));
        }
        Ginterface.updateMoney();
        Ginterface.increaseSearchValue(10);

        ProgressBar expProgress = (ProgressBar)findViewById(R.id.progressBar);
        expProgress.setProgress(Ginterface.getCurrentExp());

        ProgressBar searchProgress = (ProgressBar)findViewById(R.id.progressBar2);
        searchProgress.setProgress(Ginterface.getSearchValue());

        TextView moneyText = (TextView)findViewById(R.id.textView4);
        moneyText.setText(String.valueOf(Ginterface.getMoney()));
    }
}