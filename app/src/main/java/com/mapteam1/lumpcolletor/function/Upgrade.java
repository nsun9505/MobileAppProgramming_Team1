package com.mapteam1.lumpcolletor.function;

public class Upgrade {
    String upgradName;
    String description;
    int upgradePoint;
    double effect;
    public Upgrade(String upgradName){
        this.upgradePoint = 0;
        this.upgradName = upgradName;
        this.description = upgradName;
    }
    // 강화 비용은 스킬 포인터에 의해 결정된다.
    public String getUpgradeName() {
        return upgradName;
    }

    public int getUpgradePoint(){
        return this.upgradePoint;
    }

    public void setUpgradePoint(int upgradPoint){
        this.upgradePoint = upgradPoint;
    }

    public void setUpgradeName(String upgradName) {
        this.upgradName = upgradName;
    }

    public String getDesciption() {
        return description;
    }

    public void setDesciption(String description) {
        this.description = description;
    }
}
