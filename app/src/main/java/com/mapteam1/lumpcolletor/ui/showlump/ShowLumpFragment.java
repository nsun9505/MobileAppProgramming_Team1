package com.mapteam1.lumpcolletor.ui.showlump;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.mapteam1.lumpcolletor.lump.LumpGenerator;
import com.mapteam1.lumpcolletor.ui.ShowLumpDetail;

import java.util.ArrayList;

public class ShowLumpFragment extends Fragment {
    private ShowLumpViewModel showcharacterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        showcharacterViewModel =ViewModelProviders.of(this).get(ShowLumpViewModel.class);
        View root1 = inflater.inflate(R.layout.fragment_showlump, container, false);

        int img[] = {};

        MyAdapter adapter = new MyAdapter(
                getActivity().getApplicationContext(),
                R.layout.adapter_lump,       // GridView 항목의 레이아웃 row.xml
                LumpGenerator.getref()._test_imgs);    // 데이터
        GridView gv1 = (GridView)root1.findViewById(R.id.grid1);
        gv1.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
    //    final TextView textView1 = root1.findViewById(R.id.text_showcharacter);
        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력

        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent=new Intent(getActivity(), ShowLumpDetail.class);
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
    ArrayList<Bitmap> img;
    LayoutInflater inf;

    public MyAdapter(Context context, int layout, ArrayList<Bitmap> img) {
        this.context = context;
        this.layout = layout;
        this.img = img;
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
        iv1.setImageBitmap(img.get(position));

        return convertView;
    }
}