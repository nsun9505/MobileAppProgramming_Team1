package com.mapteam1.lumpcolletor.ui.lootlump;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcolletor.R;
import com.mapteam1.lumpcolletor.lump.LumpBlueprint;

public class LootLumpFragment extends Fragment {

    private LootLumpViewModel lootLumpViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lootLumpViewModel =
                ViewModelProviders.of(this).get(LootLumpViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lootlump, container, false);
        final TextView textView = root.findViewById(R.id.text_opencharacter);
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
                lootLumpViewModel.changeLump();
            }
        });
        /*lootLumpViewModel.getBlueprint().observe(this, new Observer<LumpBlueprint>() {
            @Override
            public void onChanged(@Nullable LumpBlueprint s) {
                imageview.setImageBitmap(s.Produce());
            }
        });*/

        return root;
    }


}