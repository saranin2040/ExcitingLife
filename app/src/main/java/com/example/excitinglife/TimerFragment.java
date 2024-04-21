package com.example.excitinglife;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;

public class TimerFragment extends Fragment {

    private TextView timerTextView;
    private Button toggleButton;
    private LottieAnimationView animationView;

    private Handler handler = new Handler();
    private long startTime = 0L;
    private boolean isRunning = false;
    private long timeInMilliseconds = 0L;
    private long timeBuffer = 0L;
    private long updatedTime = 0L;

    public TimerFragment() {
        // Required empty public constructor
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

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startTime = SystemClock.uptimeMillis();
                    handler.postDelayed(timerRunnable, 0);
                    toggleButton.setText("Stop");
                } else {
                    timeBuffer += timeInMilliseconds;
                    handler.removeCallbacks(timerRunnable);
                    toggleButton.setText("Start");
                }
                isRunning = !isRunning;
                animationView.playAnimation();
            }
        });

        return view;
    }

    private Runnable timerRunnable = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeBuffer + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs %= 60;
            int milliseconds = (int) (updatedTime % 1000);
            int tenths = milliseconds / 100;
            int hundredths = (milliseconds % 100) / 10;

            timerTextView.setText(String.format("%02d:%02d:%1d%1d", mins, secs, tenths, hundredths));
            handler.postDelayed(this, 10);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(timerRunnable); // Остановить handler при уничтожении фрагмента
    }
}
