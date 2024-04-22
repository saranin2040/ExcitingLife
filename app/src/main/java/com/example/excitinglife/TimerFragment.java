package com.example.excitinglife;

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

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int days = intent.getIntExtra("days", 0);
            int hours = intent.getIntExtra("hours", 0);
            int minutes = intent.getIntExtra("mins", 0);
            int seconds = intent.getIntExtra("secs", 0);
            int milliseconds = intent.getIntExtra("millis", 0);

            timerTextView.setText(String.format("%d:%02d:%02d:%02d:%02d", days, hours, minutes, seconds, milliseconds));
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(br, new IntentFilter(TimerService.TIMER_BR)); // Используем getActivity() для регистрации
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(br); // Используем getActivity() для отмены регистрации
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
                Intent intent = new Intent(getActivity(), TimerService.class);

                if (!isRunning) {
                    // Для запуска таймера
                    Log.d("saranin","start");
                    intent.setAction(TimerService.ACTION_START);
                    toggleButton.setText("Stop");
                } else {
                    // Для остановки таймера
                    intent.setAction(TimerService.ACTION_STOP);
                    toggleButton.setText("Start");
                }
                getActivity().startService(intent);
                isRunning = !isRunning;
                animationView.playAnimation();
            }
        });

        return view;
    }

    // Сохранение времени секундомера перед выключением устройства


//    private Runnable timerRunnable = new Runnable() {
//        public void run() {
//            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
//            updatedTime = timeBuffer + timeInMilliseconds;
//
//            int secs = (int) (updatedTime / 1000);
//            int mins = secs / 60;
//            secs %= 60;
//            int milliseconds = (int) (updatedTime % 1000);
//            int tenths = milliseconds / 100;
//            int hundredths = (milliseconds % 100) / 10;
//
//            timerTextView.setText(String.format("%02d:%02d:%1d%1d", mins, secs, tenths, hundredths));
//            handler.postDelayed(this, 10);
//        }
//    };

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        handler.removeCallbacks(timerRunnable); // Остановить handler при уничтожении фрагмента
//    }
}
