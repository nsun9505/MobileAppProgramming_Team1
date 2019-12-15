package com.mapteam1.lumpcolletor.ui.lootbox;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.function.Player;

public class LootBoxFragment extends Fragment {
    private LootBoxViewModel lootBoxViewModel;
    private static final int OPEN_BOX_MONEY = 0;
    private static final int OPEN_BOX_EXP = 1;
    private static final int OPEN_BOX_SEARCHVALUE = 2;
    private static final int OPEN_BOX_RANDOM_NUMBER = 10000;
    private static final int MULTIPLY_TYPE_SKILL_PROBABILITY = 0;
    private static final int MULTIPLY_TYPE_SKILL_EFFECT = 1;
    private static final int MULTIPLY_TYPE_SEARCHVALUE = 2;
    private static final int MULTIPLY_TYPE_MONEY = 3;
    private static final int MULTIPLY_TYPE_EXP = 4;
    private static final int MULTIPLY_TYPE_MAX_SEARCH_VALUE = 5;
    private static final int OPEN_BOX_BASE_VALUE = 100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lootBoxViewModel =ViewModelProviders.of(this).get(LootBoxViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lootbox, container, false);

        int img[] = {
                R.drawable.ic_upgrade_2,
                R.drawable.ic_upgrade_3,
                R.drawable.ic_upgrade_4};

        final int desc[] = {
                R.string.name_loot_0,
                R.string.name_loot_1,
                R.string.name_loot_2};

        MyAdapter adapter = new MyAdapter (
                getActivity().getApplicationContext(),
                R.layout.adapter_item,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터

        GridView gv = (GridView)root.findViewById(R.id.grid);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
        final TextView textView = root.findViewById(R.id.text_openitem);
        final ViewGroup tempContainer = container;
        // GridView 아이템을 클릭하면 상단 텍스트뷰에 position 출력
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                textView.setText(desc[position]);
            }
        });

        // 상자깡을 누르면 상자 오픈
        final Button boxBtn = (Button)root.findViewById(R.id.btn_lootbox);
        boxBtn.setText("상자 열기(보유 갯수 : "+Player.getPlayer().getNumOfBox()+")");
        boxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ret = Player.getPlayer().openBox();
                int getBenefit = 0;
                switch(ret) {
                    case OPEN_BOX_MONEY:
                        getBenefit = OPEN_BOX_BASE_VALUE + Player.getPlayer().getAdditionValueByMultiply(MULTIPLY_TYPE_MONEY,OPEN_BOX_BASE_VALUE);
                        Toast.makeText(tempContainer.getContext(), getBenefit + " GOLD 획득", Toast.LENGTH_SHORT).show();
                        break;
                    case OPEN_BOX_EXP:
                        getBenefit = OPEN_BOX_BASE_VALUE + Player.getPlayer().getAdditionValueByMultiply(MULTIPLY_TYPE_EXP,OPEN_BOX_BASE_VALUE);
                        Toast.makeText(tempContainer.getContext(), getBenefit + " EXP 획득", Toast.LENGTH_SHORT).show();
                        break;
                    case OPEN_BOX_SEARCHVALUE:
                        getBenefit = OPEN_BOX_BASE_VALUE + Player.getPlayer().getAdditionValueByMultiply(MULTIPLY_TYPE_SEARCHVALUE,OPEN_BOX_BASE_VALUE);
                        Toast.makeText(tempContainer.getContext(), getBenefit + " 탐색도 획득", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(tempContainer.getContext(), "열 수 있는 박스가 없습니다.", Toast.LENGTH_SHORT).show();
                        break;
                }
                boxBtn.setText("소유 박스 수 : "+Player.getPlayer().getNumOfBox());
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