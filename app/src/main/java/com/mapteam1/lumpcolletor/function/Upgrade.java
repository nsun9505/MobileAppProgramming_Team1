package com.mapteam1.lumpcolletor.function;

public class Upgrade {
    private String upgradeName;
    private String description;
    private int upgradePoint;
    private double multiply;
    private double effect;

    public Upgrade(String upgradeName){
        this.upgradePoint = 0;
        this.upgradeName = upgradeName;
        this.description = upgradeName;
        this.multiply = 0;
        this.effect = 0.0;
    }

    public void upgradeItem(){
        this.setUpgradePoint(this.getUpgradePoint() + 1);
        this.setEffect(this.getUpgradePoint() * 0.05);
    }

    public int applyEffect(int origin){
        return (int)(origin * this.getEffect());
    }

    // 강화 비용은 스킬 포인터에 의해 결정된다.
    public String getUpgradeName() {
        return upgradeName;
    }

    public int getUpgradePoint(){
        return this.upgradePoint;
    }

    public double getEffect(){
        return this.effect;
    }

    public void setUpgradePoint(int upgradPoint){
        this.upgradePoint = upgradPoint;
    }

    public void setUpgradeName(String upgradName) {
        this.upgradeName = upgradName;
    }

    public String getDesciption() {
        return description;
    }

    public void setDesciption(String description) {
        this.description = description;
    }

    public void setEffect(double effect){
        this.effect = effect;
    }

    public String nextUpgradeContent(){
        return (this.getEffect() * 100) + " -> " + (this.getEffect() + 0.05);
    }

}
