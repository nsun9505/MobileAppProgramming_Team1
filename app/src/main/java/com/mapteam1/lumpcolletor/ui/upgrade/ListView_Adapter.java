package com.mapteam1.lumpcolletor.ui.upgrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.function.Player;
import com.mapteam1.lumpcolletor.function.Upgrade;

import java.util.ArrayList;

public class ListView_Adapter extends ArrayAdapter<ListView_Upgrade> implements View.OnClickListener {
    // Activity에서 가져온 객체정보를 저장할 변수
    private ListView_Upgrade mUser;
    private Context mContext;
    private UpgradeFragment fragment;

    // ListView 내부 View들을 가르킬 변수들
    private ImageView UpgradeIcon;
    private TextView tvUserName;
    private TextView tvUserPhoneNumber;
    private Button btnSend;

    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<ListView_Upgrade> mUserData = new ArrayList<ListView_Upgrade>();

    public ListView_Adapter(Context context, ArrayList<ListView_Upgrade> upgrades, UpgradeFragment fragment) {
        super(context, 0, upgrades);
        mContext = context;
        mUserData = upgrades;
        this.fragment = fragment;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setItem(int index, ListView_Upgrade newItem){
        this.mUserData.set(index, newItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;

        // 리스트 아이템이 새로 추가될 경우에는 v가 null값이다.
        // view는 어느 정도 생성된 뒤에는 재사용이 일어나기 때문에 효율을 위해서 해준다.
        if (v == null) {
            // inflater를 이용하여 사용할 레이아웃을 가져옵니다.
            v = ((LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.adapter_upgrade, null);

            // 레이아웃이 메모리에 올라왔기 때문에 이를 이용하여 포함된 뷰들을 참조할 수 있습니다.
            UpgradeIcon = (ImageView) v.findViewById(R.id.upgrade_icon);
            tvUserName = (TextView) v.findViewById(R.id.upgrade_name);
            tvUserPhoneNumber = (TextView) v
                    .findViewById(R.id.upgrade_info);
            btnSend = (Button) v.findViewById(R.id.btn_send);
        }

        // 받아온 position 값을 이용하여 배열에서 아이템을 가져온다.
        mUser = this.getItem(position);

        // Tag를 이용하여 데이터와 뷰를 묶습니다.
        btnSend.setTag(mUser);

        // 데이터의 실존 여부를 판별합니다.
        if (mUser != null) {
            // 데이터가 있다면 갖고 있는 정보를 뷰에 알맞게 배치시킵니다.
            if (mUser.getIcon() != null) {
                UpgradeIcon.setImageDrawable(mUser.getIcon());
            }

            tvUserName.setText(mUser.getName());
            tvUserPhoneNumber.setText(mUser.getChanges());
            btnSend.setText(mUser.getCost());
            btnSend.setOnClickListener(this);
        }
        // 완성된 아이템 뷰를 반환합니다.
        return v;
    }

    // 데이터를 추가하는 것을 위해서 만들어 준다.
    public void add(ListView_Upgrade user) {
        mUserData.add(user);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        // Tag를 이용하여 Data를 가져옵니다.

        ListView_Upgrade clickItem = (ListView_Upgrade) v.getTag();
        boolean ret = false;
        switch (v.getId()) {
            case R.id.btn_send:
                ret = Player.getPlayer().upgrade(clickItem.getIndex());
                if(ret == true){
                    Upgrade upgradeItem = Player.getPlayer().getUpgradeItemByIdx(clickItem.getIndex());

                    clickItem.setName(upgradeItem.getuName());
                    clickItem.setChanges(upgradeItem.getuChanges());
                    clickItem.setCost(String.valueOf(upgradeItem.getuCost()));

                    setItem(clickItem.getIndex(), clickItem);
                    this.fragment.refresh();

                    Toast.makeText(mContext, clickItem.getName()+" 업그레이드 성공",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "골드가 부족합니다.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
