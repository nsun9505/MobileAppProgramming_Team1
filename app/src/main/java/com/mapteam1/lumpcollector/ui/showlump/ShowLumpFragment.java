package com.mapteam1.lumpcollector.ui.showlump;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcollector.R;
import com.mapteam1.lumpcollector.function.Player;
import com.mapteam1.lumpcollector.lump.Lump;
import com.mapteam1.lumpcollector.ui.ShowLumpDetail;

import java.util.ArrayList;

public class ShowLumpFragment extends Fragment {
    private ShowLumpViewModel showcharacterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        showcharacterViewModel = ViewModelProviders.of(this).get(ShowLumpViewModel.class);
        View root1 = inflater.inflate(R.layout.fragment_showlump, container, false);

        MyAdapter adapter = new MyAdapter(
                inflater.getContext(),
                R.layout.adapter_lump       // GridView 항목의 레이아웃 row.xml
                );    // 데이터
        GridView gv1 = (GridView)root1.findViewById(R.id.gv_showlump);
        gv1.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
    //    final TextView textView1 = root1.findViewById(R.id.text_showcharacter);
        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력

        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), ShowLumpDetail.class);
                intent.putExtra("index", position);
                startActivity(intent);
                //textView1.setText("position : " + position);
            }
        });
        return root1;
    }

}

class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Lump> img;
    LayoutInflater inf;

    public MyAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;
        img = Player.getPlayer().getLumpList();
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return img.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = inf.inflate(layout, null);
        ImageView iv1 = (ImageView)convertView.findViewById(R.id.characterimageView);
        Lump lump = img.get(position);
        iv1.setImageBitmap(lump.getBitmap());
        if (Player.getPlayer().isLumpActive(lump))
            iv1.setBackgroundColor(Color.YELLOW);
        else
            iv1.setBackgroundColor(Color.TRANSPARENT);

        return convertView;
    }
}