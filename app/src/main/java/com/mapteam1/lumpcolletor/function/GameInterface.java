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

    // 2차 보상
    // 레벨업 : box 수 증가
     void increaseNumberOfBox();

    // 수정 필요
    // 2차 보상
    // 상자까기 : 1차 보상의 상자를 얻거나(0), 장식을 얻거나(1)
     int openBox();

    // 수정필요
    // 3차 보상 : 장식 추가
     int addDecoration();

    // 수정 필요
    // 3차 보상 : 강화
    // 강화할 pivot을 선택 후 강화 가능
    // 먼저 강화할 pivot선택, pivot이 하나도 없어도 강화탭에 들어갈 수 있음.
    // 재화(money)를 소모하여 강화
     int reinforce(int selected);

    // 기본 정보를 출력하기 워해
     int getCurrentLevel();
     int getSearchValue();
     int getCurrentExp();
    int getMoney();
}