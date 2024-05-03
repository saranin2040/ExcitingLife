package com.example.excitinglife;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.excitinglife.BusinessLogic.BusinessLogic;
import com.example.excitinglife.BusinessLogic.Commodity.Commodity;
import com.example.excitinglife.Controller.ScreenSlidePagerAdapter;
import com.example.excitinglife.Controller.TimerControlFragment;

import java.security.AccessController;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_swipe);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.timerFragmentContainer, new TimerControlFragment())
//                    .commit();
//        }

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.view_pager, new TimerControlFragment())
//                    .commit();
//        }


        bc.put(0,new BusinessLogic(getApplicationContext().getFilesDir()));
    }

//    public void onClick(View v) {
//
//        Log.d("saranin","saving active");
//
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("saranin","saving active");

        saveObjects(); // Сохранение стопвотчей перед закрытием приложения
    }

    private void saveObjects() {
        bc.get(0).saveStopwatches();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "Saving objects on stop");
        saveObjects();  // Сохранение данных
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "Saving objects on pause");
        saveObjects();  // Сохранение данных
    }


    static public HashMap<Integer, BusinessLogic> bc=new HashMap<Integer, BusinessLogic>();

}
