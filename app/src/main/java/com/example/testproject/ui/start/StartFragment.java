package com.example.testproject.ui.start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.testproject.MainActivity;
import com.example.testproject.R;

public class StartFragment extends Fragment {
    Button btn;
    private StartViewModel startViewModel;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        startViewModel =
                ViewModelProviders.of(this).get(StartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_start, container, false);
        final TextView textView = root.findViewById(R.id.text_start);
        startViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged( String s) {
                textView.setText(s);
            }
        });
        btn = root.findViewById(R.id.gameBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).gameClear();
            }
        });

        return root;
    }

    public void GameClear(){

    }
}