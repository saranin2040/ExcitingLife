package com.example.excitinglife;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class TimerService extends Service {


    private static final int ONGOING_NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "TimerChannel";
    private static final String CHANNEL_NAME = "Timer Service Channel";

    public static final String TIMER_BR = "com.example.excitinglife.timer_br";
    Intent bi = new Intent(TIMER_BR);

    private final IBinder binder = new LocalBinder();
    private Handler handler = new Handler();
    private long startTime = SystemClock.elapsedRealtime();
    private long updatedTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeBuffer = 0L;
    private boolean isRunning = false;

    public static final String ACTION_START = "com.example.excitinglife.action.START";
    public static final String ACTION_STOP = "com.example.excitinglife.action.STOP";

    private Notification createNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
        notificationManager.createNotificationChannel(channel);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Running Timer")
                .setContentText("Timer is running in background")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .build();
    }

    private final Runnable timerRunnable = new Runnable() {
        public void run() {
            // Получаем текущее время, прошедшее с момента запуска устройства
            long elapsedTime = SystemClock.elapsedRealtime() - startTime;

            // Переводим разницу в дни, часы, минуты, секунды и миллисекунды
            int days = (int) (elapsedTime / (1000 * 60 * 60 * 24));
            int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
            int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
            int seconds = (int) ((elapsedTime / 1000) % 60);
            int milliseconds = (int) (elapsedTime % 1000);

            int truncatedMilliseconds = milliseconds / 10;

            // Подготавливаем данные для трансляции
            bi.putExtra("days", days);
            bi.putExtra("hours", hours);
            bi.putExtra("mins", minutes);
            bi.putExtra("secs", seconds);
            bi.putExtra("millis", truncatedMilliseconds);

            // Транслируем обновленное время
            sendBroadcast(bi);



            handler.postDelayed(this, 10);
        }
    };

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        super.onStartCommand(intent, flags, startId);
//        if (intent != null && "start".equals(intent.getAction())) {
//            startTimer();
//        } else if (intent != null && "stop".equals(intent.getAction())) {
//            stopTimer();
//        }
//        return START_STICKY;
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                startTimer();
                //startForeground(ONGOING_NOTIFICATION_ID, createNotification());
                Notification notification = new Notification.Builder(this, CHANNEL_ID).build();
                startForeground(ONGOING_NOTIFICATION_ID, notification);
            } else if (ACTION_STOP.equals(action)) {
                stopTimer();
                stopForeground(true);
                stopSelf();
            }
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        TimerService getService() {
            return TimerService.this;
        }
    }

    public void startTimer() {
        Log.d("saranin","isRunning="+isRunning);
        if (!isRunning) {
            // Обнуляем все временные переменные, чтобы начать отсчет с нуля
            startTime = SystemClock.elapsedRealtime();
            timeBuffer = 0L;
            timeInMilliseconds = 0L;
            updatedTime = 0L;

            handler.postDelayed(timerRunnable, 0);
            isRunning = true;
        }
    }

    public void stopTimer() {
        if (isRunning) {
            timeBuffer += timeInMilliseconds;
            handler.removeCallbacks(timerRunnable);
            isRunning = false;
        }
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(timerRunnable);
        super.onDestroy();
    }
}
