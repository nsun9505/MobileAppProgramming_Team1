package com.mapteam1.lumpcolletor.function;

public class Upgrade {
    private String uName;
    private String uChanges;
    private int uCost;
    private int uPoint;

    public Upgrade(String name){
        this.uName = name;
        this.uChanges = "0% -> 5%";
        this.uCost = 100;
        this.uPoint = 0;
    }

    public Upgrade(String name, String changes, int cost, int point){
        this.uName = name;
        this.uChanges = changes;
        this.uCost = cost;
        this.uPoint = point;
    }

    public void doUpgrade(){
        uPoint++;
        updateinfo();
    }
    public void updateinfo() {
        uCost = (1 + uPoint) * 100;
        uChanges = String.format("%d%% -> %d%%", uPoint * 5, ((uPoint+1)*5));
    }

    public int applyEffect(int origin){
        return (int)((this.getuPoint() * 0.05) * origin);
    }

    public double applyEffect(){
        return (this.getuPoint() * 0.05);
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuChanges(String uChanges) {
        this.uChanges = uChanges;
    }

    public void setuCost(int uCost) {
        this.uCost = uCost;
    }

    public void setuPoint(int uPoint) {
        this.uPoint = uPoint;
        updateinfo();
    }

    public String getuName() {
        return uName;
    }

    public String getuChanges() {
        return uChanges;
    }

    public int getuCost() {
        return uCost;
    }

    public int getuPoint() {
        return uPoint;
    }
}
