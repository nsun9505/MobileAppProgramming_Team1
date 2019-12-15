package com.mapteam1.lumpcolletor.function;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.mapteam1.lumpcolletor.lump.Lump;
import com.mapteam1.lumpcolletor.lump.LumpBlueprint;
import com.mapteam1.lumpcolletor.lump.LumpGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SaveData {
    SharedPreferences sharedPreferences;
    int level;
    int exp;
    int searchprog;
    int gold;
    int boxes;
    Set<String> lumps;
    String upgrades;
    Set<String> activelumps;

    public SaveData(Activity main) {
        sharedPreferences = main.getSharedPreferences("save_data", Context.MODE_PRIVATE);
        if(sharedPreferences!=null){
            level = sharedPreferences.getInt("level",1);
            exp = sharedPreferences.getInt("exp",0);
            searchprog = sharedPreferences.getInt("sp",50);
            gold = sharedPreferences.getInt("gold",100);
            boxes = sharedPreferences.getInt("boxes",100);
            lumps = sharedPreferences.getStringSet("lumps", null);
            upgrades = sharedPreferences.getString("upgrades","00000000");
            activelumps = sharedPreferences.getStringSet("activelumps", null);
            _test_tostring();
        }
        else{
            Log.d("???", "no Shared Preferences");
        }
    }

    public void Save() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("level", level);
        editor.putInt("exp", exp);
        editor.putInt("sp", searchprog);
        editor.putInt("gold", gold);
        editor.putInt("boxes", boxes);
        editor.putStringSet("lumps", lumps);
        editor.putString("upgrades", upgrades);
        editor.putStringSet("activelumps", activelumps);

        editor.commit();
        _test_tostring();
    }

    public void loadUpgradesFromList(int[] data) {
        int upgradeKind = 6;
        if (data.length < upgradeKind) return;
        StringBuilder upgradeData = new StringBuilder();
        for(int i = 0; i < upgradeKind; i++) {
            upgradeData.append((char)('0'+data[i]));
        }
        upgrades = upgradeData.toString();
    }

    public int[] translateUpgradesString() {
        int upgradeKind = 6;
        if (upgrades.length() < upgradeKind) return null;
        int[] upgradeArray = new int[upgradeKind];
        for(int i = 0; i < upgradeKind; i++) {
            upgradeArray[i] = upgrades.charAt(i) - '0';
        }
        return upgradeArray;
    }

    public void loadLumpsFromArrayList(ArrayList<Lump> data) {
        if (lumps == null) lumps = new HashSet<>();
        if (data == null) return;
        int lumpCount = data.size();
        if (lumpCount == 0) return;
        lumps.clear();
        for(int i = 0; i < lumpCount; i++) {
            lumps.add(data.get(i).Encode());
        }
    }

    public void translateLumpsStringSet(ArrayList<Lump> lumpList) {
        if (lumpList == null || lumps == null) return;
        String[] lumpSet = lumps.toArray(new String[lumps.size()]);
        Lump lump;
        lumpList.clear();
        for(int i = 0; i < lumpSet.length; i++) {
            lump = new Lump(lumpSet[i]);
            lumpList.add(lump);
        }
    }

    public void loadActiveLumpsFromArrayList(ArrayList<Lump> all, ArrayList<Lump> active) {
        if (activelumps == null) activelumps = new HashSet<>();
        if (all == null || active == null) return;
        int lumpCount = active.size();
        if (lumpCount == 0) return;
        activelumps.clear();
        for(int i = 0; i < lumpCount; i++) {
            activelumps.add(String.valueOf(all.indexOf(active.get(i))));
        }
    }

    public void translateActiveLumpsStringSet(ArrayList<Lump> all, ArrayList<Lump> active) {
        if (all == null || active == null || activelumps == null) return;
        String[] lumpSet = activelumps.toArray(new String[activelumps.size()]);
        active.clear();
        int index;
        for(int i = 0; i < lumpSet.length; i++) {
            index = Integer.parseInt(lumpSet[i]);
            active.add(all.get(index));
        }
    }

    public void _test_tostring() {
        StringBuilder stringBuilder = new StringBuilder();
        /*stringBuilder.append(level);
        stringBuilder.append(exp);
        stringBuilder.append(searchprog);
        stringBuilder.append(gold);
        stringBuilder.append(boxes);*/
        stringBuilder.append(upgrades);
        /*if (lumps != null) {
            String[] array = lumps.toArray(new String[lumps.size()]);

            for (String item : array) {
                Log.d("???", item);
            }

            stringBuilder.append(lumps.size());
        }*/
        Log.d("???", stringBuilder.toString());
    }
}
