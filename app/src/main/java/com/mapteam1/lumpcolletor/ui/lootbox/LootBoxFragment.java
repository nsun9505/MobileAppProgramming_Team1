package com.mapteam1.lumpcolletor.ui.lootbox;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcolletor.R;

public class LootBoxFragment extends Fragment {
    private LootBoxViewModel lootBoxViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lootBoxViewModel =ViewModelProviders.of(this).get(LootBoxViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lootbox, container, false);

        int img[] = {};

        MyAdapter adapter = new MyAdapter (
                getActivity().getApplicationContext(),
                R.layout.adapter_item,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터
        GridView gv = (GridView)root.findViewById(R.id.grid);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
        final TextView textView = root.findViewById(R.id.text_openitem);
        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                textView.setText("position : " + position);
            }
        });
        return root;
    }

}
class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    int img[];
    LayoutInflater inf;

    public MyAdapter(Context context, int layout, int[] img) {
        this.context = context;
        this.layout = layout;
        this.img = img;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return img[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = inf.inflate(layout, null);
        ImageView iv = (ImageView)convertView.findViewById(R.id.itemimageView);
        iv.setImageResource(img[position]);

        return convertView;
    }
}