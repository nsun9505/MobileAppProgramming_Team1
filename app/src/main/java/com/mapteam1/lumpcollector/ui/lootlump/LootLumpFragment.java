package com.mapteam1.lumpcollector.ui.lootlump;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcollector.R;
import com.mapteam1.lumpcollector.function.Player;

public class LootLumpFragment extends Fragment {
    private static final String LOOT_LUMP_TEXT = "덩어리 뽑기 (필요 탐색도: %d, 현재 : %d)";
    private static final int LOOT_LUMP_COST = 100;

    private LootLumpViewModel lootLumpViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        lootLumpViewModel =
                ViewModelProviders.of(this).get(LootLumpViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lootlump, container, false);
        final TextView textView = root.findViewById(R.id.LootLumpText);
        lootLumpViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final ImageView imageview = root.findViewById(R.id.LootLumpImage);
        lootLumpViewModel.getBitmap().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(@Nullable Bitmap s) {
                imageview.setImageBitmap(s);
            }
        });
        final Button button = root.findViewById(R.id.LootLumpButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                boolean ret = Player.getPlayer().getParts(LOOT_LUMP_COST);
                if (ret) {
                    lootLumpViewModel.createLump();
                    button.setText(String.format(LOOT_LUMP_TEXT, LOOT_LUMP_COST, Player.getPlayer().getSearchValue()));
                } else {
                    Toast.makeText(container.getContext(), "탐색도가 부족합니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
        button.setText(String.format(LOOT_LUMP_TEXT, LOOT_LUMP_COST, Player.getPlayer().getSearchValue()));

        return root;
    }


}