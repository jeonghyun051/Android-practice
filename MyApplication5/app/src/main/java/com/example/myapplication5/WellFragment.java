package com.example.myapplication5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WellFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity at = (MainActivity) container.getContext();
        View view = inflater.inflate(R.layout.fragement_well,container,false);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
