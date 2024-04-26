package com.example.excitinglife.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.example.excitinglife.MainActivity;
import com.example.excitinglife.R;
import com.example.excitinglife.TimerService;

public class TimerFragment extends Fragment {
    public TimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        //MainActivity.bc.startStopwatch(0);
        //getActivity().registerReceiver(br, new IntentFilter(TimerService.TIMER_BR)); // Используем getActivity() для регистрации
    }

    @Override
    public void onPause() {
        super.onPause();
        //MainActivity.bc.stopStopwatch(0);
        //getActivity().unregisterReceiver(br); // Используем getActivity() для отмены регистрации
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);

        timerTextView = view.findViewById(R.id.timerTextView);
        toggleButton = view.findViewById(R.id.toggleButton);
        animationView = view.findViewById(R.id.animation_view);

        animationView.setRenderMode(RenderMode.HARDWARE);

        timerTextView.setText(getElapsedTimeString());

        if (MainActivity.bc.get(0).getStopwatchs().get(0).isLaunch()) {
            Log.d("saranin","start");
            MainActivity.bc.get(0).startStopwatch(0);
            toggleButton.setText("Stop");
            handler.postDelayed(timerRunnable, 0);
        } else {
            Log.d("saranin","stop");
            MainActivity.bc.get(0).stopStopwatch(0);
            toggleButton.setText("Start");
            handler.removeCallbacks(timerRunnable);
        }

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), TimerService.class);

                if (!MainActivity.bc.get(0).getStopwatchs().get(0).isLaunch()) {
                    Log.d("saranin","start");
                    MainActivity.bc.get(0).startStopwatch(0);
                    toggleButton.setText("Остановить");
                    handler.postDelayed(timerRunnable, 0);
                } else {
                    Log.d("saranin","stop");
                    MainActivity.bc.get(0).stopStopwatch(0);
                    toggleButton.setText("Старт");
                    handler.removeCallbacks(timerRunnable);
                }
                //getActivity().startService(intent);
                animationView.playAnimation();
            }
        });

        return view;
    }

    private String getElapsedTimeString()
    {
        long elapsedTime = MainActivity.bc.get(0).getStopwatchs().get(0).getElapsedTime();

        int days = (int) (elapsedTime / (1000 * 60 * 60 * 24));
        int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
        int seconds = (int) ((elapsedTime / 1000) % 60);
        int milliseconds = (int) (elapsedTime % 1000);
        milliseconds = milliseconds / 10;

        return String.format("%d:%02d:%02d:%02d:%02d", days, hours, minutes, seconds, milliseconds);
    }

    private final Runnable timerRunnable = new Runnable() {
        public void run() {
            timerTextView.setText(getElapsedTimeString());
            handler.postDelayed(this, 10);
        }
    };



    private TextView timerTextView;
    private Button toggleButton;
    private LottieAnimationView animationView;

    private Handler handler = new Handler();

}
