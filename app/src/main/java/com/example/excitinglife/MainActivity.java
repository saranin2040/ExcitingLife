package com.example.excitinglife;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.excitinglife.Controller.ScreenSlidePagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_swipe);
        //Intent serviceIntent = new Intent(this, TimerService.class);
        //serviceIntent.setAction(TimerService.ACTION_START);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(serviceIntent);
//        } else {
//            startService(serviceIntent);
//        }

        //stopService(serviceIntent); // Остановка службы
        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Сохранение времени секундомера перед выключением устройства
//        saveTimerStartTime();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // Восстановление времени секундомера после перезагрузки устройства
//        restoreTimerStartTime();
//        // Запуск или продолжение таймера
//        startTimer();
//    }
//
//    private void updateTime() {
//        // Обновление времени секундомера
//        // Ваш код обновления времени
//    }
//
//    private void saveTimerStartTime() {
//        // Сохранение времени секундомера перед выключением устройства
//        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putLong("startTime", startTime);
//        editor.apply();
//    }
//
//    private void restoreTimerStartTime() {
//        // Восстановление времени секундомера после перезагрузки устройства
//        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        startTime = prefs.getLong("startTime", 0);
//    }
//
//    private void startTimer() {
//        // Запуск или продолжение таймера
//        if (startTime > 0) {
//            // Если есть сохраненное время, продолжаем отсчет с момента, на котором остановились
//            handler.postDelayed(timerRunnable, 0);
//        } else {
//            // Иначе начинаем отсчет с нуля
//            startTime = SystemClock.uptimeMillis();
//            handler.postDelayed(timerRunnable, 0);
//        }
//    }
}
