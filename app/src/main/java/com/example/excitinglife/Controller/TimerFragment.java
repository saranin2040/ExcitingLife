package com.example.excitinglife.Controller;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.example.excitinglife.BusinessLogic.Stopwatch.Stopwatch;
import com.example.excitinglife.SmartLog;
import com.example.excitinglife.MainActivity;
import com.example.excitinglife.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.text.InputType;
import android.widget.Toast;


public class TimerFragment extends Fragment {
    public TimerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);


        //загрузка таймеров
        for (Integer stopwatchId : MainActivity.bc.get(0).getStopwatchs().keySet())
        {
            addnewStopwatch(view,stopwatchId);
            SmartLog.logUseful("Load stopwatch "+stopwatchId);
        }

        Button addTimerButton = view.findViewById(R.id.addTimerButton);
        addTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Введи как будешь обзывать свой таймер");

                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Кнопка "OK", которая добавляет таймер с введенным названием
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String timerName = input.getText().toString();
                        if (!timerName.isEmpty()) {
                            Integer id = MainActivity.getBc().addStopwatch(timerName);
                            addnewStopwatch(view, id);
                            SmartLog.logUseful("New stopwatch " + id + " with name " + timerName);
                        } else {
                            Toast.makeText(getActivity(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        SmartLog.logUseful("Отмена подписывания договора дьявола");
                        dialog.cancel();
                    }
                });

                builder.show(); // Показываем диалоговое окно
            }
        });
        return view;
    }


    private void addnewStopwatch(View view, Integer id)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View timerControlView = inflater.inflate(R.layout.timer_control_layout, null);
        LinearLayout container = view.findViewById(R.id.timerContainer);

        String timerId = "timer" + container.getChildCount();
        timerControlView.setTag(timerId);

        TextView daysValue = timerControlView.findViewById(R.id.daysValue);
        TextView hoursValue = timerControlView.findViewById(R.id.hoursValue);
        TextView minutesValue = timerControlView.findViewById(R.id.minutesValue);
        TextView secondsValue = timerControlView.findViewById(R.id.secondsValue);
        TextView millisecondsValue = timerControlView.findViewById(R.id.millisecondsValue);

        TextView timerName = timerControlView.findViewById(R.id.textTimerName);

        Button toggleButton = timerControlView.findViewById(R.id.toggleButton);
        Button closeButton = timerControlView.findViewById(R.id.closeButton);

        toggleButton.setOnClickListener(new TimerControlFragment(timerName,daysValue,hoursValue,minutesValue,secondsValue,millisecondsValue,toggleButton,closeButton,id));
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                container.removeView(timerControlView);
                MainActivity.getBc().removeStopwatch(id);
                SmartLog.logUseful("Stopwatch " + id + " removed");
            }
        });

        container.addView(timerControlView);
    }
}
