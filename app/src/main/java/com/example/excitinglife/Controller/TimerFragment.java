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
                Integer id= MainActivity.getBc().addStopwatch("chocolate fish");
                addnewStopwatch(view,id);

                SmartLog.logUseful("New stopwatch "+id);
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

        TextView timerValueTextView = timerControlView.findViewById(R.id.timerTextView);

        Button toggleButton = timerControlView.findViewById(R.id.toggleButton);
        Button closeButton = timerControlView.findViewById(R.id.closeButton);

        toggleButton.setOnClickListener(new TimerControlFragment(timerValueTextView,toggleButton,closeButton,id));
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
