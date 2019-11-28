package com.mapteam1.lumpcolletor.ui.upgrade;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.mapteam1.lumpcolletor.R;

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
        View root1 = inflater.inflate(R.layout.fragment_upgrade, container, false);
        ListView_Adapter adapter = new ListView_Adapter(
                getActivity().getApplicationContext());
        ListView lv = (ListView)root1.findViewById(R.id.upgrade_list);
        lv.setAdapter(adapter);
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
        return root1;
    }

}
