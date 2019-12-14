package com.mapteam1.lumpcolletor.ui.upgrade;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.function.Player;
import com.mapteam1.lumpcolletor.function.Upgrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListView_Upgrade_main  extends Fragment {
   // private ListView userList;
    //private ListView_Adapter adapter;

    /** Called when the activity is first created. */
    @Override
 /*   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO Auto-generated method stub
        setContentView(R.layout.fragment_upgrade);

        // Adapter 생성
        adapter = new ListView_Adapter(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        userList = (ListView) findViewById(R.id.upgrade_list);
        userList.setAdapter(adapter);

        // Data 추가
        ListView_Upgrade u1 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.a,null), "김씨", "010-1234-5678");
        adapter.add(u1);

        ListView_Upgrade u2 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.a,null), "이씨", "010-8765-4321");
        adapter.add(u2);

        ListView_Upgrade u3 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.a,null), "박씨", "010-0000-0000");
        adapter.add(u3);

        // Data가 변경 되있음을 알려준다.
        adapter.notifyDataSetChanged();
    } */

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ArrayList<ListView_Upgrade> upgradeList = new ArrayList<ListView_Upgrade>();
        ArrayList<Upgrade> upgradeItems = Player.getPlayer().getUpgradeList();

        ListView_Upgrade u1 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.ic_upgrade_0,null), upgradeItems.get(0).getuName(), upgradeItems.get(0).getuChanges(), String.valueOf(upgradeItems.get(0).getuCost()), 0);
        upgradeList.add(u1);

        ListView_Upgrade u2 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.ic_upgrade_1,null), upgradeItems.get(1).getuName(), upgradeItems.get(1).getuChanges(), String.valueOf(upgradeItems.get(1).getuCost()), 1);
        upgradeList.add(u2);

        ListView_Upgrade u3 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.ic_upgrade_2,null), upgradeItems.get(2).getuName(), upgradeItems.get(2).getuChanges(), String.valueOf(upgradeItems.get(2).getuCost()), 2);
        upgradeList.add(u3);

        ListView_Upgrade u4 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.ic_upgrade_3,null), upgradeItems.get(3).getuName(), upgradeItems.get(3).getuChanges(), String.valueOf(upgradeItems.get(3).getuCost()), 3);
        upgradeList.add(u4);

        ListView_Upgrade u5 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.ic_upgrade_4,null), upgradeItems.get(4).getuName(), upgradeItems.get(4).getuChanges(), String.valueOf(upgradeItems.get(4).getuCost()), 4);
        upgradeList.add(u5);

        ListView_Upgrade u6 = new ListView_Upgrade(getResources().getDrawable(
                R.drawable.ic_upgrade_5,null), upgradeItems.get(5).getuName(), upgradeItems.get(5).getuChanges(), String.valueOf(upgradeItems.get(5).getuCost()), 5);
        upgradeList.add(u6);

        View root1 = inflater.inflate(R.layout.fragment_upgrade, container, false);
        ListView_Adapter adapter = new ListView_Adapter(getActivity().getApplicationContext(), upgradeList, this);

        ListView lv = (ListView)root1.findViewById(R.id.upgrade_list);
        lv.setAdapter(adapter);

        // Data가 변경 되있음을 알려준다.
        adapter.notifyDataSetChanged();
        return root1;
    }

    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

}
