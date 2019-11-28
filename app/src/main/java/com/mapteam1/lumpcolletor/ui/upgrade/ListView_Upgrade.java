package com.mapteam1.lumpcolletor.ui.upgrade;

import android.graphics.drawable.Drawable;

public class ListView_Upgrade {
    private Drawable mUserIcon;
    private String mUserName;
    private String mUserPhoneNumber;

    ListView_Upgrade(Drawable userIcon, String userName, String userPhoneNumber) {
        mUserIcon = userIcon;
        mUserName = userName;
        mUserPhoneNumber = userPhoneNumber;
    }

    public Drawable getUserIcon(){return mUserIcon; }

    public String getUserName() {
        return mUserName;
    }

    public String  getUserPhoneNumber() {
        return mUserPhoneNumber;
    }
}
