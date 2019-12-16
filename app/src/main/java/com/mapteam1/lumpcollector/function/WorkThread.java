package com.mapteam1.lumpcollector.function;

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
            final Player player = Player.getPlayer();
            int oldMoney = player.getMoney();
            float oldSearchValue = player.getSearchValue();
            int oldLevel = player.getCurrentLevel();
            float oldExp = player.getCurrentExp();
            int oldMaxSearchValue = player.getMaxSearchValue();
            int oldNumberOfBox = player.getNumOfBox();

            while(true) {
                if (oldExp != player.getCurrentExp()) {
                    Message message = mHandler.obtainMessage();
                    int cur = player.getCurrentExp();
                    int max = player.getMaxExp();
                    oldExp = MathUtils.lerp(oldExp, cur, 0.2f);
                    message.what = UPDATE_EXP; // 메시지 ID
                    message.obj = cur + "/" + max;
                    message.arg1 = (int) (oldExp * 100) / max;
                    mHandler.sendMessage(message);
                }
                if (oldLevel != player.getCurrentLevel()) {
                    Message message = mHandler.obtainMessage();
                    oldLevel = player.getCurrentLevel();
                    String level = "LV " + String.valueOf(oldLevel);
                    message.what = UPDATE_LEVEL;
                    message.obj = level;
                    mHandler.sendMessage(message);
                }
                if(oldSearchValue != player.getSearchValue()) {
                    Message message = mHandler.obtainMessage();
                    int cur = player.getSearchValue();
                    int max = player.getMaxSearchValue();
                    oldSearchValue = MathUtils.lerp(oldSearchValue, cur, 0.2f);
                    message.what = UPDATE_SEARCHVALUE;
                    message.obj = cur + "/" + max;
                    message.arg1 = (int) (oldSearchValue * 100) / max;
                    mHandler.sendMessage(message);
                }
                if (oldMoney != player.getMoney()) {
                    Message message = mHandler.obtainMessage();
                    oldMoney = (int)MathUtils.lerp(oldMoney, player.getMoney(), 0.2f);
                    String money = String.valueOf(oldMoney);
                    message.what = UPDATE_MONEY;
                    message.obj = money;
                    mHandler.sendMessage(message);
                }
                if (oldMaxSearchValue != player.getMaxSearchValue()){
                    Message message = mHandler.obtainMessage();
                    int cur = player.getSearchValue();
                    oldMaxSearchValue = player.getMaxSearchValue();
                    message.what = UPDATE_MAX_SEARCH_VALUE;
                    message.obj =  cur + "/"+ oldMaxSearchValue;
                    message.arg1 = (int) (cur * 100) / oldMaxSearchValue;
                    mHandler.sendMessage(message);
                }
                if(oldNumberOfBox != player.getNumOfBox()){
                    Message message = mHandler.obtainMessage();
                    oldNumberOfBox = player.getNumOfBox();
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
