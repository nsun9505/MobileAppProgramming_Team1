package com.mapteam1.lumpcolletor.function;

public interface GameInterface {
     void UpdateMaxExp();

    // 1차 보상 : 경험치
    // 경험치 증가 -> MAX 도달 시 LEVELUP 리턴
     int increaseExp(int getExp);

    // 1차 보상 : 탐색도 증가
    // 탐색도 증가 : 100% 도달 시 GETPIVOT 리턴, 아직 MAX가 100%가 아니면 NOTGETPIVOT
     int increaseSearchValue(int getsearchValue);

    // 1차 보창 : 재화
     void updateMoney();

    // 레벨업 : level 증가
     void levelUp();

    // 2차 보상 레벨업 : box 수 증가
    void increaseNumberOfBox();
    // 2차 보상 덩어리 얻기 : 탐색도 감소
    boolean getParts(int cost);

    // 수정 필요
    // 2차 보상
    // 상자까기 : 1차 보상의 상자를 얻거나(0), 장식을 얻거나(1)
     int openBox();


    // 기본 정보를 출력하기 워해
     int getCurrentLevel();
     int getSearchValue();
     int getCurrentExp();
    int getMoney();
    int getMaxSearchValue();
    int getMaxExp();
}