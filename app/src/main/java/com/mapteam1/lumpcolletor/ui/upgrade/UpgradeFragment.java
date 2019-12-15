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

public class UpgradeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ArrayList<ListView_Upgrade> upgradeList = new ArrayList<ListView_Upgrade>();
        ArrayList<Upgrade> upgradeItems = Player.getPlayer().getUpgradeList();

        int[] img = {
                R.drawable.ic_upgrade_0,
                R.drawable.ic_upgrade_1,
                R.drawable.ic_upgrade_2,
                R.drawable.ic_upgrade_3,
                R.drawable.ic_upgrade_4,
                R.drawable.ic_upgrade_5 };

        Upgrade upgrade;
        ListView_Upgrade upgradeStructure;
        for(int i = 0; i < img.length; i++) {
            upgrade = upgradeItems.get(i);
            upgradeStructure = new ListView_Upgrade(getResources().getDrawable(img[i], null), upgrade.getuName(), upgrade.getuChanges(), String.valueOf(upgrade.getuCost()), i);
            upgradeList.add(upgradeStructure);
        }

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
