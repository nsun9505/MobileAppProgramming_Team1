package com.mapteam1.lumpcolletor.ui.lootlump;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mapteam1.lumpcolletor.R;

public class LootLumpFragment extends Fragment {

    private LootLumpViewModel lootLumpViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
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
        return root;
    }
}