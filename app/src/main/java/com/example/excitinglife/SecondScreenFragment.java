package com.example.excitinglife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class SecondScreenFragment extends Fragment {

    public SecondScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.screen_second, container, false);

        // Ты можешь выполнить любые действия по инициализации View-компонентов здесь, если это необходимо
        // Например, установить текст для TextView
        TextView numberTextView = view.findViewById(R.id.numberTextView);
        numberTextView.setText("42"); // Просто пример, измените на нужную логику

        return view;
    }
}
