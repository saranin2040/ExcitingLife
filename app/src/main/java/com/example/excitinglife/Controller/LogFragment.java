package com.example.excitinglife.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.excitinglife.SmartLog;
import com.example.excitinglife.R;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class LogFragment extends Fragment {

    private TextView logTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);
        logTextView = view.findViewById(R.id.logTextView);

        // Подписываемся на LiveData
        SmartLog.text.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                logTextView.setText(s);
            }
        });

        return view;
    }

}
