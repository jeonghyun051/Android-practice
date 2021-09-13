package com.example.myapplication5;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    private TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Context context = container.getContext();
        MainActivity at = (MainActivity) context;

        View view = inflater.inflate(R.layout.fragment_person,container, false);
//
//        tvTitle = view.findViewById(R.id.tv_title);
//        tvTitle.setText("Fragment Search");
        return view;
    }
}