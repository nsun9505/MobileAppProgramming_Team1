package com.mapteam1.lumpcolletor.lump;

import androidx.core.math.MathUtils;

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

    public int activateAddition(int matchType, int value) {
        if (matchType == type && new Random().nextInt(100) <= chance)
            return value * effect / 100;
        return 0;
    }

    public static LumpSkill Random(int power) {
        Random r = new Random();
        int type = r.nextInt(3);
        int chance = Math.max(r.nextInt(Math.min(100, power)), 1);
        int effect = Math.max(1, power - chance);

        return new LumpSkill(type, chance, effect);
    }

    public static LumpSkill FullRandom() {
        Random r = new Random();
        return Random(20+r.nextInt(40)+ Player.getPlayer().getCurrentLevel());
    }

    public String description() {
        String target = "%d%% 확률로 기분 %d%% 증가";
        switch(type) {
            case 0:
                target = "%d%% 확률로 경험치 %d%% 추가 획득";
                break;
            case 1:
                target = "%d%% 확률로 탐색도 %d%% 추가 획득";
                break;
            case 2:
                target = "%d%% 확률로 골드 %d%% 추가 획득";
                break;
        }
        return String.format(target, chance, effect);
    }
}
