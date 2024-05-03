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
import com.example.excitinglife.MainActivity;
import com.example.excitinglife.R;
import com.example.excitinglife.SmartLog;

public class TimerControlFragment extends Fragment implements View.OnClickListener{

    private Button toggleButton;
    private LottieAnimationView animationView;
    private Handler handler = new Handler();

    public TimerControlFragment(TextView timerValueTextView,Button toggleButton,Integer id) {
        textView=timerValueTextView;
        this.toggleButton=toggleButton;
        this.id=id;

        Log.d("saranin","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        textView.setText(getElapsedTimeString());

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

        // Required empty public constructor
    }

    @Override
    public void onClick(View v)
    {
        SmartLog.logUseful("SARANIN "+id);
        //textView.setText(String.valueOf(count));



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



        // Получаем идентификатор timerControl из родительского View
//                        int timerControlId = ((ViewGroup) v.getParent()).getId();
//                        if (timerControlId != View.NO_ID) {
//                            Log.d("SARANIN", "Clicked on toggleButton in timerControl with ID: " + getResources().getResourceEntryName(timerControlId));
//                        } else {
//                            Log.d("SARANIN", "No valid ID found for the parent of toggleButton");
//                        }
    }

    private final Runnable timerRunnable = new Runnable() {
        public void run() {
            textView.setText(getElapsedTimeString());
            handler.postDelayed(this, 10);
        }
    };

    private String getElapsedTimeString()
    {
        long elapsedTime = MainActivity.bc.get(0).getStopwatchs().get(id).getElapsedTime();

        int days = (int) (elapsedTime / (1000 * 60 * 60 * 24));
        int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
        int seconds = (int) ((elapsedTime / 1000) % 60);
        int milliseconds = (int) (elapsedTime % 1000);
        milliseconds = milliseconds / 10;

        return String.format("%d:%02d:%02d:%02d:%02d", days, hours, minutes, seconds, milliseconds);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timer_control_layout, container, false);

        Button toggleButton = view.findViewById(R.id.toggleButton);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем идентификатор timerControl из родительского View
                int timerControlId = ((ViewGroup) v.getParent()).getId();
                Log.d("SARANIN", "Clicked on toggleButton in timerControl with ID: " + getResources().getResourceEntryName(timerControlId));
            }
        });

        return view;
    }

    int id;
    TextView textView;


}
