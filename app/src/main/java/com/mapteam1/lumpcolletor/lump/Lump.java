package com.mapteam1.lumpcolletor.lump;

import android.graphics.Bitmap;

public class Lump {
    private final static String REGEX = "_";
    LumpBlueprint lbp;
    Bitmap imgCache;
    LumpSkill skill;

    public Lump(LumpBlueprint lbp) {
        this.lbp = lbp;
        skill = LumpSkill.FullRandom();
    }

    public Lump(String data) {
        lbp = new LumpBlueprint();
        String[] info = data.split(REGEX);
        if (info.length > 3) {
            lbp.Decode(info[0]);
            int type = Integer.parseInt(info[1]);
            int chance = Integer.parseInt(info[2]);
            int effect = Integer.parseInt(info[3]);
            skill = new LumpSkill(type, chance, effect);
        } else {
            lbp.Decode(data);
            skill = LumpSkill.FullRandom();
        }
    }

    public Bitmap getBitmap() {
        if (lbp == null) return null;
        if (imgCache == null)
            imgCache = lbp.Produce();
        return imgCache;
    }

    public String Encode() {
        if (lbp == null) return null;
        String data = lbp.Encode();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(data);
        stringBuilder.append(REGEX);
        stringBuilder.append(skill.type);
        stringBuilder.append(REGEX);
        stringBuilder.append(skill.chance);
        stringBuilder.append(REGEX);
        stringBuilder.append(skill.effect);

        return stringBuilder.toString();
    }

    public String GetSkillDescription() {
        return skill.description();
    }

    public int getSkillEffect(int matchType, int value, double bonusChance, double bonueEffect) {
        return skill.activateAddition(matchType, value, bonusChance, bonueEffect);
    }
}
