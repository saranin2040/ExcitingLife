package com.example.excitinglife.Controller.StopwatchPageController.StopwatchFragmentController;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.example.excitinglife.BusinessLogic.Stopwatch.Stopwatch;
import com.example.excitinglife.MainActivity;
import com.example.excitinglife.SmartLog;

public class StartButtonClickListener extends Fragment implements View.OnClickListener{
    public StartButtonClickListener(Integer id)
    {
        this.id=id;

        setTimerValue(MainActivity.getBc().getStopwatchs().get(id).getElapsedTime());

        actionButton(MainActivity.getBc().getStopwatchs().get(id).isLaunch());
    }

    @Override
    public void onClick(View v)
    {
        SmartLog.logUseful("SARANIN "+id);
        actionButton(!MainActivity.getBc().getStopwatchs().get(id).isLaunch());
    }

    private void actionButton(boolean isStart)
    {
        if (isStart) {
            MainActivity.bc.get(0).startStopwatch(id);
            MainActivity.getVisual().paintStartStopwatchState(id);
            handler.postDelayed(timerRunnable, 0);
        } else
        {
            if (MainActivity.bc.get(0).getStopwatchs().get(id).isStart()) {
                MainActivity.bc.get(0).stopStopwatch(id);
                MainActivity.getVisual().paintStopStopwatchState(id);
                handler.removeCallbacks(timerRunnable);
            }
            else {
                MainActivity.getVisual().paintBeginStopwatchState(id);
            }
        }
    }

    private void setTimerValue(long elapsedTime)
    {
        int days = (int) (elapsedTime / (1000 * 60 * 60 * 24));
        int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
        int seconds = (int) ((elapsedTime / 1000) % 60);
        int milliseconds = (int) (elapsedTime % 1000);
        milliseconds = milliseconds / 10;

        MainActivity.getVisual().updateTextTimer(id,days, hours, minutes, seconds, milliseconds);
    }

    private final Runnable timerRunnable = new Runnable() {
        public void run()
        {
            Stopwatch stopwatch = MainActivity.getBc().getStopwatchs().get(id);
            if (stopwatch==null)
            {
                handler.removeCallbacks(timerRunnable);
            }
            else
            {
                setTimerValue(stopwatch.getElapsedTime());
                handler.postDelayed(this, 10);
            }
        }
    };

    private Handler handler = new Handler();
    private int id;

}
