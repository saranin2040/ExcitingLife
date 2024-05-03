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

        //int toggleButtonId = View.generateViewId();
////        timerTextView = view.findViewById(R.id.timerTextView);
//        toggleButton = view.findViewById(R.id.toggleButton);
//        //animationView = view.findViewById(R.id.animation_view);
//        toggleButton.setId(toggleButtonId);
//
//        //animationView.setRenderMode(RenderMode.HARDWARE);
//
//        toggleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Получаем идентификатор timerControl из родительского View
//                int timerControlId = ((ViewGroup) v.getParent()).getId();
//                Log.d("SARANIN", "Clicked on toggleButton in timerControl with ID: " + getResources().getResourceEntryName(timerControlId));
//            }
//        });


        for (Integer stopwatchId : MainActivity.bc.get(0).getStopwatchs().keySet())
        {

            LayoutInflater inflater2 = LayoutInflater.from(getContext());
            View timerControlView = inflater2.inflate(R.layout.timer_control_layout, null);
            LinearLayout container2 = view.findViewById(R.id.timerContainer);
            //timerControlView.setId(View.generateViewId());

            String timerId = "timer" + container2.getChildCount(); // Генерация уникального ID
            timerControlView.setTag(timerId);

            TextView timerValueTextView = timerControlView.findViewById(R.id.timerTextView);

            // Найдем кнопку toggleButton в созданном экземпляре timer_control_layout
            Button toggleButton = timerControlView.findViewById(R.id.toggleButton);

            // Добавляем новый экземпляр timer_control_layout в родительский контейнер
//                ViewGroup rootView = view.getRootView().findViewById(R.id.timerContainer); // Замените timerContainer на ID вашего родительского контейнера
//                rootView.addView(timerControlView);

            // Устанавливаем этот фрагмент как слушателя для кнопки toggleButton
            c++;


            SmartLog.logUseful("Load stopwatch "+stopwatchId);

            toggleButton.setOnClickListener(new TimerControlFragment(timerValueTextView,toggleButton,stopwatchId));
//                        new View.OnClickListener() {
//                    public int count = c;
//                    @Override
//                    public void onClick(View v)
//                    {
//                        SmartLog.logUseful("SARANIN "+count);
//                        timerValueTextView.setText(String.valueOf(count));
//
//                        // Получаем идентификатор timerControl из родительского View
////                        int timerControlId = ((ViewGroup) v.getParent()).getId();
////                        if (timerControlId != View.NO_ID) {
////                            Log.d("SARANIN", "Clicked on toggleButton in timerControl with ID: " + getResources().getResourceEntryName(timerControlId));
////                        } else {
////                            Log.d("SARANIN", "No valid ID found for the parent of toggleButton");
////                        }
//                    }
//                });

            container2.addView(timerControlView);
        }


        Button addTimerButton = view.findViewById(R.id.addTimerButton);

        // Устанавливаем этот фрагмент как слушателя для кнопки
        addTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View timerControlView = inflater.inflate(R.layout.timer_control_layout, null);
                LinearLayout container = view.findViewById(R.id.timerContainer);
                //timerControlView.setId(View.generateViewId());

                String timerId = "timer" + container.getChildCount(); // Генерация уникального ID
                timerControlView.setTag(timerId);

                TextView timerValueTextView = timerControlView.findViewById(R.id.timerTextView);

                // Найдем кнопку toggleButton в созданном экземпляре timer_control_layout
                Button toggleButton = timerControlView.findViewById(R.id.toggleButton);

                // Добавляем новый экземпляр timer_control_layout в родительский контейнер
//                ViewGroup rootView = view.getRootView().findViewById(R.id.timerContainer); // Замените timerContainer на ID вашего родительского контейнера
//                rootView.addView(timerControlView);

                // Устанавливаем этот фрагмент как слушателя для кнопки toggleButton
                c++;

                Integer id= MainActivity.bc.get(0).addStopwatch("chocolate fish");
                SmartLog.logUseful("New stopwatch "+id);

                toggleButton.setOnClickListener(new TimerControlFragment(timerValueTextView,toggleButton,id));
//                        new View.OnClickListener() {
//                    public int count = c;
//                    @Override
//                    public void onClick(View v)
//                    {
//                        SmartLog.logUseful("SARANIN "+count);
//                        timerValueTextView.setText(String.valueOf(count));
//
//                        // Получаем идентификатор timerControl из родительского View
////                        int timerControlId = ((ViewGroup) v.getParent()).getId();
////                        if (timerControlId != View.NO_ID) {
////                            Log.d("SARANIN", "Clicked on toggleButton in timerControl with ID: " + getResources().getResourceEntryName(timerControlId));
////                        } else {
////                            Log.d("SARANIN", "No valid ID found for the parent of toggleButton");
////                        }
//                    }
//                });

                container.addView(timerControlView);
            }
        });

//        timerTextView.setText(getElapsedTimeString());
//
//        if (MainActivity.bc.get(0).getStopwatchs().get(0).isLaunch()) {
//            Log.d("saranin","start");
//            MainActivity.bc.get(0).startStopwatch(0);
//            toggleButton.setText("Stop");
//            handler.postDelayed(timerRunnable, 0);
//        } else {
//            Log.d("saranin","stop");
//            MainActivity.bc.get(0).stopStopwatch(0);
//            toggleButton.setText("Start");
//            handler.removeCallbacks(timerRunnable);
//        }
//
//        toggleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent intent = new Intent(getActivity(), TimerService.class);
//
//                if (!MainActivity.bc.get(0).getStopwatchs().get(0).isLaunch()) {
//                    Log.d("saranin","start");
//                    MainActivity.bc.get(0).startStopwatch(0);
//                    toggleButton.setText("Остановить");
//                    handler.postDelayed(timerRunnable, 0);
//                } else {
//                    Log.d("saranin","stop");
//                    MainActivity.bc.get(0).stopStopwatch(0);
//                    toggleButton.setText("Старт");
//                    handler.removeCallbacks(timerRunnable);
//                }
//                //getActivity().startService(intent);
//                //animationView.playAnimation();
//
//            }
//        });

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
    private int c = 0;

}
