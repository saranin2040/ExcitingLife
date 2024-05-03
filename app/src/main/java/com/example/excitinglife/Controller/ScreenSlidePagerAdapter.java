package com.example.excitinglife.Controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // Новый порядок страниц: 0 - LogFragment, 1 - TimerFragment, 2 - SecondScreenFragment
        switch (position) {
            case 0:
                return new LogFragment(); // Новый фрагмент с логами
            case 1:
                return new TimerFragment(); // Существующий фрагмент с таймером
            case 2:
                return new SecondScreenFragment(); // Существующий второй экран
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}

