package com.example.excitinglife.Controller;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.example.excitinglife.BusinessLogic.Stopwatch.Stopwatch;
import com.example.excitinglife.MainActivity;
import com.example.excitinglife.R;
import com.example.excitinglife.SmartLog;

public class TimerControlFragment extends Fragment implements View.OnClickListener{
    public TimerControlFragment(TextView timerName,TextView daysValue,TextView hoursValue,TextView minutesValue,TextView secondsValue,TextView millisecondsValue,Button toggleButton,Button closeButton,Integer id)
    {
        this.timerName=timerName;

        this.daysValue=daysValue;
        this.hoursValue=hoursValue;
        this.minutesValue=minutesValue;
        this.secondsValue=secondsValue;
        this.millisecondsValue=millisecondsValue;

        this.toggleButton=toggleButton;
        this.closeButton=closeButton;
        this.id=id;

        setTimerValue(MainActivity.getBc().getStopwatchs().get(id).getElapsedTime());
        timerName.setText(MainActivity.getBc().getStopwatchs().get(id).getName());

        if (MainActivity.bc.get(0).getStopwatchs().get(id).isLaunch()) {
            Log.d("saranin","start");
            MainActivity.bc.get(0).startStopwatch(id);
            toggleButton.setText("Stop");
            handler.postDelayed(timerRunnable, 0);
        } else {
            Log.d("saranin","stop");
            MainActivity.bc.get(0).stopStopwatch(id);
            toggleButton.setText("Start");
            handler.removeCallbacks(timerRunnable);
        }
    }

    @Override
    public void onClick(View v)
    {
        SmartLog.logUseful("SARANIN "+id);

        if (!MainActivity.bc.get(0).getStopwatchs().get(id).isLaunch()) {
                    Log.d("saranin","start");
                    MainActivity.bc.get(0).startStopwatch(id);
                    toggleButton.setText("Остановить");
                    handler.postDelayed(timerRunnable, 0);
                } else {
                    Log.d("saranin","stop");
                    MainActivity.bc.get(0).stopStopwatch(id);
                    toggleButton.setText("Старт");
                    handler.removeCallbacks(timerRunnable);
                }
    }

    private final Runnable timerRunnable = new Runnable() {
        public void run()
        {
            Stopwatch stopwatch = MainActivity.getBc().getStopwatchs().get(id);
            if (stopwatch==null)
            {
                handler.removeCallbacks(timerRunnable);
            }
            else {
                setTimerValue(stopwatch.getElapsedTime());
                handler.postDelayed(this, 10);
            }
        }
    };

    private String getElapsedTimeString(long elapsedTime)
    {
        int days = (int) (elapsedTime / (1000 * 60 * 60 * 24));
        int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
        int seconds = (int) ((elapsedTime / 1000) % 60);
        int milliseconds = (int) (elapsedTime % 1000);
        milliseconds = milliseconds / 10;

        return String.format("%d:%02d:%02d:%02d:%02d", days, hours, minutes, seconds, milliseconds);
    }

    private void setTimerValue(long elapsedTime)
    {
        int days = (int) (elapsedTime / (1000 * 60 * 60 * 24));
        int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
        int seconds = (int) ((elapsedTime / 1000) % 60);
        int milliseconds = (int) (elapsedTime % 1000);
        milliseconds = milliseconds / 10;

        daysValue.setText(String.format("%d:",days));
        hoursValue.setText(String.format("%02d:",hours));
        minutesValue.setText(String.format("%02d:",minutes));
        secondsValue.setText(String.format("%02d:",seconds));
        millisecondsValue.setText(String.format("%02d",milliseconds));
    }

    private Button toggleButton;
    private Button closeButton;
    private TextView timerName;
    TextView daysValue;
    TextView hoursValue;
    TextView minutesValue;
    TextView secondsValue;
    TextView millisecondsValue;
    private LottieAnimationView animationView;
    private Handler handler = new Handler();
    private int id;

}
