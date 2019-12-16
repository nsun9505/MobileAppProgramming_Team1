package com.mapteam1.lumpcollector.function;

import com.mapteam1.lumpcollector.lump.Lump;

import java.util.ArrayList;

public class Player {
    private static final int LEVELUP = 1;
    private static final int NOLEVELUP = 0;
    private static final int MAXSEARCHVALUE = 1;
    private static final int NOTMAXSEARCHVALUE = 0;
    private static final int COMPLETEPIVOT = 1;
    private static final int NOTCOMPLETEPIVOT = 0;
    private static final int MULTIPLY_TYPE_SKILL_CHANCE = 0;
    private static final int MULTIPLY_TYPE_SKILL_EFFECT = 1;
    private static final int MULTIPLY_TYPE_SEARCHVALUE = 2;
    private static final int MULTIPLY_TYPE_MONEY = 3;
    private static final int MULTIPLY_TYPE_EXP = 4;
    private static final int MULTIPLY_TYPE_MAX_SEARCH_VALUE = 5;
    private static final int OPEN_BOX_MONEY = 0;
    private static final int OPEN_BOX_EXP = 1;
    private static final int OPEN_BOX_SEARCHVALUE = 2;
    private static final int OPEN_BOX_BASE_VALUE = 100;

    private static final int NUMBER_OF_UPGRADE = 6;
    private int maxSearchValue;
    private int maxExp;

    private int level;                // 레벨
    private int currentExp;                // 경험치
    private int searchValue;        // 탐색도(탐색치)
    private int money;                // 재화
    private int numOfBox;            // 상자 개수
    private double multiply;
    private ArrayList<Upgrade> upgradeList = null;

    // 가지고 있는 장식리스트
    private ArrayList<Decoration> decoList;
    private ArrayList<Lump> lumpList;
    private ArrayList<Lump> activeLumpList;
    private int maxActiveLump = 3;

    // 싱글톤
    private static Player myPlayer;

    private Player(){
        this.multiply = 0.0;
        this.level = 1;
        this.currentExp = 0;
        this.searchValue = 0;
        this.money = 0;
        this.numOfBox = 0;
        this.maxExp = 100;
        this.maxSearchValue = 100;
        this.decoList = new ArrayList<Decoration>();
        this.upgradeList = new ArrayList<Upgrade>();
        upgradeList.add(0, new Upgrade("스킬 발동 확률 증가"));
        upgradeList.add(1, new Upgrade("스킬 발동 효과 증가"));
        upgradeList.add(2, new Upgrade("탐색도 획득량 증가"));
        upgradeList.add(3, new Upgrade("골드 획득량 증가"));
        upgradeList.add(4, new Upgrade("경험치 획득량 증가"));
        upgradeList.add(5, new Upgrade("최대 탐색도 증가"));
        this.lumpList = new ArrayList<>();
        this.activeLumpList = new ArrayList<>();
    }

    private static class MyPlayer{
        public static final Player PLAYER = new Player();
    }
    public static Player getPlayer() {
        return MyPlayer.PLAYER;
    }
    // 1차 보상 : 경험치
    // 경험치 증가 -> MAX 도달 시 LEVELUP 리턴
    public int increaseExp(int getExp) {
        // 현재 경험치 갱신
        this.setExp(this.getCurrentExp() + getExp + this.getAdditionValueByMultiply(MULTIPLY_TYPE_EXP, getExp));

        // 현재 경험치를 받아옴.
        int currentExp = this.getCurrentExp();

        // 현재 경험치가 현재 레벨에서 다음 레벨로 넘어가기 위한 경험치를 달성한 경우
        // 경험치를 갱신하고, 레벨을 증가시키기 위한 값 리턴
        // 다음 레벨에 맞는 최대 경험치 갱신
        if (currentExp >= this.getMaxExp()) {
            this.setExp(currentExp - this.getMaxExp());
            this.UpdateMaxExp();
            this.increaseNumberOfBox();
            this.levelUp();
            return LEVELUP;
        }

        // 레벨업 하지 않음.
        return NOLEVELUP;
    }

    public void UpdateMaxExp() {
        this.setMaxExp(100 + (this.getCurrentLevel() - 1) * 20);
    }

    public void UpdateMaxSearchValue() {
        maxSearchValue = (int) (100 * (upgradeList.get(MULTIPLY_TYPE_MAX_SEARCH_VALUE).applyEffect() + 1));
    }

    // 1차 보상 : 탐색도 증가
    // 탐색도 증가 : 100% 도달 시 GETPIVOT 리턴, 아직 MAX가 100%가 아니면 NOTGETPIVOT
    public int increaseSearchValue(int getSearchValue) {
        // 현재 탐색도를 게임 클리어를 통해 얻은 탐색도만큼 증가시킨다.
        this.setSearchValue(getSearchValue() + getSearchValue + getAdditionValueByMultiply(MULTIPLY_TYPE_SEARCHVALUE, getSearchValue));

        if (this.getSearchValue() >= this.getMaxSearchValue()) {
            this.setSearchValue(this.getMaxSearchValue());
            return MAXSEARCHVALUE;
        }
        return NOTMAXSEARCHVALUE;
    }

    public boolean getParts(int cost){
        if(cost > this.getSearchValue()){
            return false;
        }
        this.setSearchValue(this.getSearchValue() - cost);
        return true;
    }

    // 1차 보창 : 재화
    public void updateMoney() {
        // 1~200 사이의 재화를 랜덤하게 얻음.
        int getMoney = getRandom(200);
        this.setMoney(this.getMoney() + getMoney + getAdditionValueByMultiply(MULTIPLY_TYPE_MONEY, getMoney));
    }

    // 레벨업 : level 증가
    public void levelUp() {
        this.setLevel(this.getCurrentLevel() + 1);
    }

    // 2차 보상
    // 레벨업 : box 수 증가
    public void increaseNumberOfBox() {
        this.setNumOfBox(this.getNumOfBox() + 1);
    }

    // 수정 필요
    // 2차 보상
    // 상자까기 : 1차 보상의 상자를 얻거나(0), 장식을 얻거나(1)
    public int openBox() {
        // 상자 소모
        // 0개라면 상자를 오픈할 수 없음. -1 리턴
        if (this.getNumOfBox() == 0)
            return -1;

        // 상자 소모
        this.setNumOfBox(getNumOfBox() - 1);

        // 랜덤으로 1~100사이의 숫자 생성
        int num = this.getRandom(OPEN_BOX_BASE_VALUE);
        int ret = num%3;
        switch(ret){
            case OPEN_BOX_MONEY:
                this.openBoxGetMoney();
                break;
            case OPEN_BOX_EXP:
                this.increaseExp(OPEN_BOX_BASE_VALUE);
                break;
            case OPEN_BOX_SEARCHVALUE:
                this.increaseSearchValue(OPEN_BOX_BASE_VALUE);
                break;
        }

        // 상자를 열어서 얻을 수 있는 재화의 양은 1~200사이
        return ret;
    }

    private int getRandom(int limit) {
        return (int) (Math.random() * limit) + 1;
    }

    public boolean upgrade(int upgradeIdx){
        Upgrade upgradeItem = this.getUpgradeItemByIdx(upgradeIdx);
        int curMoney = this.getMoney();
        int needMoney = upgradeItem.getuCost();

        // 가지고 있는 돈이 부족한 경우 upgrade 실패
        if(needMoney > curMoney)
            return false;

        // 최대 탐색도 증가 업그레이드 시 수행
        upgradeItem.doUpgrade();
        if(upgradeIdx == MULTIPLY_TYPE_MAX_SEARCH_VALUE)
            UpdateMaxSearchValue();
        //    this.setMaxSearchValue(this.getMaxSearchValue() + upgradeItem.applyEffect(this.getMaxSearchValue()));
        this.getUpgradeList().set(upgradeIdx, upgradeItem);
        this.setMoney(this.getMoney() - needMoney);
        return true;
    }

    public int getAdditionValueByMultiply(int type, int origin){
        Upgrade item = this.getUpgradeList().get(type);
        return item.applyEffect(origin) + getSkillEffects(type, origin);
    }

    public int openBoxGetMoney(){
        int ret = OPEN_BOX_BASE_VALUE + getAdditionValueByMultiply(MULTIPLY_TYPE_MONEY, OPEN_BOX_BASE_VALUE);
        this.setMoney(this.getMoney() + ret);
        return ret;
    }

    public Upgrade getUpgradeItemByIdx(int idx){
        return this.getUpgradeList().get(idx);
    }

    public int getCurrentLevel() {
        return this.level;
    }

    public int getCurrentExp() {
        return this.currentExp;
    }

    public int getSearchValue() {
        return this.searchValue;
    }

    public int getMoney() {
        return this.money;
    }

    public int getNumOfBox() {
        return this.numOfBox;
    }

    public int getMaxSearchValue() { return this.maxSearchValue; }

    public int getMaxExp() {
        return this.maxExp;
    }

    public double getMultiply(){
        return this.multiply;
    }

    public ArrayList<Upgrade> getUpgradeList(){
        return this.upgradeList;
    }

    private void setMultiply(double multiply){
        this.multiply = multiply;
    }

    private void setMoney(int money) {
        this.money = money;
    }

    private void setNumOfBox(int numOfBox) {
        this.numOfBox = numOfBox;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    private void setSearchValue(int value) {
        this.searchValue = value;
    }

    private void setExp(int exp) {
        this.currentExp = exp;
    }

    private void setMaxExp(int newMaxExp) {
        this.maxExp = newMaxExp;
    }

    private void setMaxSearchValue(int maxSearchValue){
        this.maxSearchValue = maxSearchValue;
    }

    public ArrayList<Lump> getLumpList() {
        return lumpList;
    }

    public int getSkillEffects(int type, int origin) {
        if (type == MULTIPLY_TYPE_SEARCHVALUE || type == MULTIPLY_TYPE_MONEY || type == MULTIPLY_TYPE_EXP) {
            int i, bonus = 0;
            double bonusChance = upgradeList.get(MULTIPLY_TYPE_SKILL_CHANCE).applyEffect();
            double bonusEffect = upgradeList.get(MULTIPLY_TYPE_SKILL_EFFECT).applyEffect();

            for(i = 0; i < getActiveLumpCount(); i++) {
                bonus += activeLumpList.get(i).getSkillEffect(type, origin, bonusChance, bonusEffect);
            }
            return bonus;
        } else
            return 0;
    }

    public boolean isLumpActive(Lump lump) {
        return (activeLumpList.contains(lump));
    }

    public boolean setLumpActive(Lump lump) {
        if (!activeLumpList.contains(lump) && activeLumpList.size() < maxActiveLump) {
            activeLumpList.add(lump);
            return true;
        }
        return false;
    }

    public boolean setLumpInactive(Lump lump) {
        if (activeLumpList.contains(lump)) {
            activeLumpList.remove(lump);
            return true;
        }
        return false;
    }

    public int getActiveLumpCount() {
        return activeLumpList.size();
    }
    public int getMaxActiveLump() {
        return maxActiveLump;
    }

    public void Load(SaveData saveData) {
        level = saveData.level;
        currentExp = saveData.exp;
        searchValue = saveData.searchprog;
        money = saveData.gold;
        numOfBox = saveData.boxes;

        saveData.translateLumpsStringSet(lumpList);
        int[] upgradePoints = saveData.translateUpgradesString();
        for(int i = 0; i < upgradePoints.length; i++) {
            upgradeList.get(i).setuPoint(upgradePoints[i]);
        }
        saveData.translateActiveLumpsStringSet(lumpList, activeLumpList);

        UpdateMaxExp();
        UpdateMaxSearchValue();
        maxExp = getMaxExp();
        maxSearchValue = getMaxSearchValue();
    }

    public void Save(SaveData saveData) {
        saveData.level = level;
        saveData.exp = currentExp;
        saveData.searchprog = searchValue;
        saveData.gold = money;
        saveData.boxes = numOfBox;
        saveData.loadLumpsFromArrayList(lumpList);
        int upgradeNumber = upgradeList.size();
        int[] upgradePoints = new int[upgradeNumber];
        for(int i = 0; i < upgradeNumber; i++) {
            upgradePoints[i] = upgradeList.get(i).getuPoint();
        }
        saveData.loadUpgradesFromList(upgradePoints);
        saveData.loadActiveLumpsFromArrayList(lumpList, activeLumpList);
        saveData.Save();
    }
}
