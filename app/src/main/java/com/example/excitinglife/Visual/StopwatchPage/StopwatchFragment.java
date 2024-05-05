package com.example.excitinglife.Visual.StopwatchPage;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class StopwatchFragment
{

    public StopwatchFragment(TextView daysValue, TextView hoursValue, TextView minutesValue, TextView secondsValue, TextView millisecondsValue, Button toggleButton,
                             LinearLayout container, View timerControlView) {
        this.daysValue = daysValue;
        this.hoursValue = hoursValue;
        this.minutesValue = minutesValue;
        this.secondsValue = secondsValue;
        this.millisecondsValue = millisecondsValue;
        this.toggleButton=toggleButton;
        this.container=container;
        this.timerControlView=timerControlView;
    }

    public void setTimer(int days,int hours,int minutes,int seconds,int milliseconds)
    {
        daysValue.setText(String.format("%d:",days));
        hoursValue.setText(String.format("%02d:",hours));
        minutesValue.setText(String.format("%02d:",minutes));
        secondsValue.setText(String.format("%02d:",seconds));
        millisecondsValue.setText(String.format("%02d",milliseconds));
    }

    public void setTextStartButton(String string)
    {
        toggleButton.setText(string);
    }

    public void clear()
    {
        container.removeView(timerControlView);
    }

    private Button toggleButton;
    private Button closeButton;
    private TextView timerName;
    private TextView daysValue;
    private TextView hoursValue;
    private TextView minutesValue;
    private TextView secondsValue;
    private TextView millisecondsValue;
    private LottieAnimationView animationView;
    private LinearLayout container;
    private View timerControlView;
}
