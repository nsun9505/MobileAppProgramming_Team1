package com.mapteam1.lumpcolletor.function;

public class Upgrade {
    private String uName;
    private String uChanges;
    private int uCost;
    private int uPoint;

    public Upgrade(String name, String changes, int cost, int point){
        this.uName = name;
        this.uChanges = changes;
        this.uCost = cost;
        this.uPoint = point;
    }

    public void doUpgrade(){
        this.setuPoint(this.getuPoint() + 1);
        this.setuCost((this.getuPoint()+1) * 100);
        String newChanges = (this.getuPoint() * 5) + "% -> " + ((this.getuPoint()+1)*5)+"%";
        this.setuChanges(newChanges);
    }

    public int applyEffect(int origin){
        return (int)((this.getuPoint() * 0.05) * origin);
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
