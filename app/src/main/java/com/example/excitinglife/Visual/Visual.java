package com.example.excitinglife.Visual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.excitinglife.Controller.StopwatchPageController.StopwatchFragmentController.CloseButtonClickListener;
import com.example.excitinglife.Controller.StopwatchPageController.StopwatchFragmentController.StartButtonClickListener;
import com.example.excitinglife.R;
import com.example.excitinglife.Visual.StopwatchPage.StopwatchFragment;

import java.util.HashMap;

public class Visual
{

    public void paintNewTimer(int id, String nameStopwatch, View view,Context context)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
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
        timerName.setText(nameStopwatch);

        Button toggleButton = timerControlView.findViewById(R.id.toggleButton);
        Button closeButton = timerControlView.findViewById(R.id.closeButton);

        stopwatchFragments.put(id,new StopwatchFragment(daysValue,hoursValue,minutesValue,secondsValue,millisecondsValue,toggleButton,container,timerControlView));

        toggleButton.setOnClickListener(new StartButtonClickListener(id));
        closeButton.setOnClickListener(new CloseButtonClickListener(id));

        container.addView(timerControlView);
    }

    public void updateTextTimer(Integer id,int days,int hours,int minutes,int seconds,int milliseconds)
    {
        stopwatchFragments.get(id).setTimer(days, hours, minutes, seconds, milliseconds);
    }

    public void setTextStartButtonStopwatch(Integer id, String string)
    {
        stopwatchFragments.get(id).setTextStartButton(string);
    }

    public void clearStopwatch(Integer id)
    {
        stopwatchFragments.get(id).clear();
        stopwatchFragments.remove(id);
    }


    private HashMap<Integer, StopwatchFragment> stopwatchFragments=new HashMap<Integer, StopwatchFragment>();
}
