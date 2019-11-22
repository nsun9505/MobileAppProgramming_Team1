package com.example.testproject.function;


import android.widget.TextView;

import com.example.testproject.R;

import java.util.ArrayList;
import java.util.Random;

public class Player implements GameInterface {
    private static final int LEVELUP = 1;
    private static final int NOLEVELUP = 0;
    private static final int GETPARTS = 1;
    private static final int NOTGETPARTS = 0;
    private static final int COMPLETEPIVOT = 1;
    private static final int NOTCOMPLETEPIVOT = 0;

    private int maxExp;
    private int maxSearchValue;
    private int level;                // 레벨
    private int currentExp;                // 경험치
    private int searchValue;        // 탐색도(탐색치)
    private int money;                // 재화
    private int numOfBox;            // 상자 개수
    // 가지고 있는 장식리스트
    private ArrayList<Decoration> decoList;

    public Player() {
        this.level = 1;
        this.currentExp = 0;
        this.searchValue = 0;
        this.money = 0;
        this.numOfBox = 0;
        this.maxExp = 100;
        this.maxSearchValue = 100;
        this.decoList = new ArrayList<Decoration>();
    }

    // 1차 보상 : 경험치
    // 경험치 증가 -> MAX 도달 시 LEVELUP 리턴
    public int increaseExp(int getExp) {
        // 현재 경험치 갱신
        this.setExp(this.getCurrentExp() + getExp);

        // 현재 경험치를 받아옴.
        int currentExp = this.getCurrentExp();

        // 현재 경험치가 현재 레벨에서 다음 레벨로 넘어가기 위한 경험치를 달성한 경우
        // 경험치를 갱신하고, 레벨을 증가시키기 위한 값 리턴
        // 다음 레벨에 맞는 최대 경험치 갱신
        if (currentExp >= this.getMaxExp()) {
            this.setExp(this.getCurrentExp() - this.getMaxExp());
            this.UpdateMaxExp();
            return LEVELUP;
        }

        // 레벨업 하지 않음.
        return NOLEVELUP;
    }

    public void UpdateMaxExp() {
        this.setMaxExp(100 + (this.getCurrentLevel() - 1) * 20);
    }

    // 1차 보상 : 탐색도 증가
    // 탐색도 증가 : 100% 도달 시 GETPIVOT 리턴, 아직 MAX가 100%가 아니면 NOTGETPIVOT
    public int increaseSearchValue(int getSearchValue) {
        // 현재 탐색도를 게임 클리어를 통해 얻은 탐색도만큼 증가시킨다.
        this.setSearchValue(getSearchValue() + getSearchValue);


        if (this.getSearchValue() >= this.getMaxSearchValue()) {
            this.setSearchValue(this.getSearchValue() - this.getMaxSearchValue());
            this.updateMaxSearchValue();
            return GETPARTS;
        }
        return NOTGETPARTS;
    }

    private void updateMaxSearchValue() {
        this.setMaxSearchValue(100 + (this.getCurrentLevel() - 1) * 20);
    }

    // 1차 보창 : 재화
    public void updateMoney() {
        // 1~200 사이의 재화를 랜덤하게 얻음.
        int getMoney = getRandom(200);
        this.setMoney(this.getMoney() + getMoney);
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
        // 1~90사이의 수는 Money, 91~100 사이의 수는 장식 획득
        int num = getRandom(100);

        // 상자를 열어서 얻을 수 있는 재화의 양은 1~200사이
        if (num >= 1 && num <= 90)
            return 1;                // 1일 경우 updateMoney 실행
        else
            return 2;                // 2일 경우 addDecoration 실행
    }

    private int getRandom(int limit) {
        return (int) (Math.random() * limit) + 1;
    }

    // 3차 보상 : 장식 추가
    public int addDecoration() {
        // 랜덤으로 장식 생성 후 추가
        Decoration newDeco = new Decoration();
        ArrayList<Decoration> curDecoList = this.getDecorationList();
        curDecoList.add(newDeco);
        return 1;                    // SUCCESS
    }

    // 수정 필요
    // 3차 보상 : 강화
    // 강화할 pivot을 선택 후 강화 가능
    public int reinforce(int selected) {
        // 먼저 강화할 pivot선택, pivot이 하나도 없어도 강화탭에 들어갈 수 있음.
        // 재화(money)를 소모하여 강화
        ArrayList<Decoration> curDecoList = this.getDecorationList();

        // 현재 가지고 있는 장식의 개수가 0또는 개수가 현재 가지고 있는 양보다 넘는다면 에러, 리턴 -1
        if (curDecoList.size() == 0 || curDecoList.size() < selected)
            return -1;

        // 강화할 아이템 선택
        Decoration reinforced_deco = curDecoList.get(selected);

		/*
		if(this.getMoney() < reinforced_deco.getNeedMoney())
			return -1;
		*/

        // 선택된 장식 강화 후 현재 장식 리스트 갱신
        // reinforced_deco.reinforce();
        // curDecoList.set(selected, reinforced_deco);
        return 1;                                        // SUCCESS
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

    public ArrayList<Decoration> getDecorationList() {
        return this.decoList;
    }

    public int getNumOfBox() {
        return this.numOfBox;
    }

    private int getMaxSearchValue() {
        return this.maxSearchValue;
    }

    private int getMaxExp() {
        return this.maxExp;
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

    private void setMaxSearchValue(int newMaxSearchValue) {
        this.searchValue = newMaxSearchValue;
    }
}
