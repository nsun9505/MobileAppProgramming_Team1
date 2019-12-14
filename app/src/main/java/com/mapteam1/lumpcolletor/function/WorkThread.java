package com.mapteam1.lumpcolletor.function;

import android.os.Handler;
import android.os.Message;

import com.google.android.material.math.MathUtils;

public class WorkThread extends Thread {
    public static final int UPDATE_LEVEL = 1;
    public static final int UPDATE_MONEY = 2;
    public static final int UPDATE_EXP = 3;
    public static final int UPDATE_SEARCHVALUE = 4;
    public static final int UPDATE_MAX_SEARCH_VALUE = 5;
    public static final int UPDATE_NUM_OF_BOX = 6;
    public static final int SEND_STOP = 0;
    boolean stopped = false;
    Handler mHandler;

    public WorkThread(Handler mHandler){
        stopped = false;
        this.mHandler = mHandler;
    }
    public void stopThread() { // THREAD STOP 버튼이 클릭되면 호출됨
        stopped = true;
    }
    @Override
    public void run() {
        super.run();
        while(stopped == false) {
            int oldMoney = Player.getPlayer().getMoney();
            float oldSearchValue = Player.getPlayer().getSearchValue();
            int oldLevel = Player.getPlayer().getCurrentLevel();
            float oldExp = Player.getPlayer().getCurrentExp();
            int oldMaxSearchValue = Player.getPlayer().getMaxSearchValue();
            int oldNumberOfBox = Player.getPlayer().getNumOfBox();

            while(true) {
                if (oldExp != Player.getPlayer().getCurrentExp()) {
                    Message message = mHandler.obtainMessage();
                    int cur = Player.getPlayer().getCurrentExp();
                    int max = Player.getPlayer().getMaxExp();
                    oldExp = MathUtils.lerp(oldExp, cur, 0.2f);
                    message.what = UPDATE_EXP; // 메시지 ID
                    message.obj = cur + "/" + max;
                    message.arg1 = (int) (oldExp * 100) / max;
                    mHandler.sendMessage(message);
                }
                if (oldLevel != Player.getPlayer().getCurrentLevel()) {
                    Message message = mHandler.obtainMessage();
                    oldLevel = Player.getPlayer().getCurrentLevel();
                    String level = "LV " + String.valueOf(oldLevel);
                    message.what = UPDATE_LEVEL;
                    message.obj = level;
                    mHandler.sendMessage(message);
                }
                if(oldSearchValue != Player.getPlayer().getSearchValue()) {
                    Message message = mHandler.obtainMessage();
                    int cur = Player.getPlayer().getSearchValue();
                    int max = Player.getPlayer().getMaxSearchValue();
                    oldSearchValue = MathUtils.lerp(oldSearchValue, cur, 0.2f);
                    message.what = UPDATE_SEARCHVALUE;
                    message.obj = cur + "/" + max;
                    message.arg1 = (int) (oldSearchValue * 100) / max;
                    mHandler.sendMessage(message);
                }
                if (oldMoney != Player.getPlayer().getMoney()) {
                    Message message = mHandler.obtainMessage();
                    oldMoney = (int)MathUtils.lerp(oldMoney, Player.getPlayer().getMoney(), 0.2f);
                    String money = String.valueOf(oldMoney);
                    message.what = UPDATE_MONEY;
                    message.obj = money;
                    mHandler.sendMessage(message);
                }
                if(oldNumberOfBox != Player.getPlayer().getNumOfBox()){
                    Message message = mHandler.obtainMessage();
                    oldNumberOfBox = Player.getPlayer().getNumOfBox();
                    String numOfBox = String.valueOf(oldNumberOfBox);
                    message.what = UPDATE_NUM_OF_BOX;
                    message.obj = numOfBox;
                    mHandler.sendMessage(message);
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
