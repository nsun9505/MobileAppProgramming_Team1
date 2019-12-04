package com.mapteam1.lumpcolletor.function;

import android.os.Handler;
import android.os.Message;

import com.google.android.material.math.MathUtils;

public class WorkThread extends Thread {
    public static final int UPDATE_LEVEL = 1;
    public static final int UPDATE_MONEY = 2;
    public static final int UPDATE_EXP = 3;
    public static final int UPDATE_SEARCHVALUE = 4;
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

            while(true) {
                if (oldExp != Player.getPlayer().getCurrentExp()) {
                    Message message = mHandler.obtainMessage();
                    oldExp = MathUtils.lerp(oldExp, Player.getPlayer().getCurrentExp(), 0.2f);
                    message.what = UPDATE_EXP; // 메시지 ID
                    message.arg1 = (int) (oldExp * 100) / Player.getPlayer().getMaxExp();
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
                    oldSearchValue = MathUtils.lerp(oldSearchValue, Player.getPlayer().getSearchValue(), 0.2f);
                    message.what = UPDATE_SEARCHVALUE;
                    message.arg1 = (int) (oldSearchValue * 100) / Player.getPlayer().getMaxSearchValue();
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
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
