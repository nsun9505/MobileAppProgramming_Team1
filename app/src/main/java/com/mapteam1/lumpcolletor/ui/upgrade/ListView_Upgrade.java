package com.mapteam1.lumpcolletor.ui.upgrade;

import android.graphics.drawable.Drawable;

public class ListView_Upgrade {
    private Drawable mIcon;
    private String mName;
    private String mChanges;
    private String mCost;
    private int mIndex;

    ListView_Upgrade(Drawable icon, String name, String changes, String cost, int index) {
        mIcon = icon;
        mName = name;
        mChanges = changes;
        mCost = cost;
        mIndex = index;
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

    public int getIndex(){return mIndex;}

    public void setIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setChanges(String mChanges) {
        this.mChanges = mChanges;
    }

    public void setCost(String mCost) {
        this.mCost = mCost;
    }


}
