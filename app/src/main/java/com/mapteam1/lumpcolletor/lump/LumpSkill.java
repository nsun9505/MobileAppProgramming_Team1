package com.mapteam1.lumpcolletor.lump;

import com.mapteam1.lumpcolletor.function.Player;

import java.util.Random;

public class LumpSkill {
    int type;
    int chance;
    int effect;

    public LumpSkill(int type, int chance, int effect) {
        this.type = type;
        this.chance = chance;
        this.effect = effect;
    }

    public int activateAddition(int matchType, int value, double bonusChance, double bonueEffect) {
        double finalChance = chance * (1+bonusChance/100);
        double finalEffect = effect * (1+bonueEffect/100);
        if (matchType == type && new Random().nextInt(100) <= finalChance)
            return (int)(value * finalEffect / 100);
        return 0;
    }

    public static LumpSkill Random(int power) {
        Random r = new Random();
        int type = 2 + r.nextInt(3);
        int chance = Math.max(r.nextInt(Math.min(100, power)), 1);
        int effect = Math.max(1, power - chance);

        return new LumpSkill(type, chance, effect);
    }

    public static LumpSkill FullRandom() {
        Random r = new Random();
        return Random(20+r.nextInt(40)+ Player.getPlayer().getCurrentLevel());
    }

    public String description() {
        String target;
        switch(type) {
            case 2:
                target = "%s%% 확률로 탐색도 %s%% 추가 획득";
                break;
            case 3:
                target = "%s%% 확률로 골드 %s%% 추가 획득";
                break;
            case 4:
                target = "%s%% 확률로 경험치 %s%% 추가 획득";
                break;
            default:
                target = "%s%% 확률로 기분 %s%% 증가";
                break;
        }
        String strBonus = "%d(+%d)";
        int finalChance = Player.getPlayer().getAdditionValueByMultiply(0, chance);
        int finalEffect = Player.getPlayer().getAdditionValueByMultiply(1, effect);
        String strChance = (finalChance > 0)?String.format(strBonus, chance+finalChance, finalChance):String.valueOf(chance);
        String strEffect = (finalEffect > 0)?String.format(strBonus, effect+finalEffect, finalEffect):String.valueOf(effect);
        return String.format("활성화 시: "+target, strChance, strEffect);
    }
}
