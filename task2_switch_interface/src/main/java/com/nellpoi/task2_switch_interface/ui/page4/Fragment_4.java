package com.nellpoi.task2_switch_interface.ui.page4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nellpoi.task2_switch_interface.R;

public class Fragment_4 extends Fragment {

    private Fragment_4_ViewModel fragment_4_ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_4_ViewModel = ViewModelProviders.of(this).get(Fragment_4_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_page4, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        fragment_4_ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
