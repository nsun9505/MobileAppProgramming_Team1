package com.mapteam1.lumpcolletor.ui.upgrade;

import android.graphics.drawable.Drawable;

public class ListView_Upgrade {
    private Drawable mIcon;
    private String mName;
    private String mChanges;
    private String mCost;

    ListView_Upgrade(Drawable icon, String name, String changes, String cost) {
        mIcon = icon;
        mName = name;
        mChanges = changes;
        mCost = cost;
    }

    public Drawable getIcon(){return mIcon; }

    public String getName() {
        return mName;
    }

    public String getChanges() {
        return mChanges;
    }

    public String  getCost() {
        return mCost;
    }
}
