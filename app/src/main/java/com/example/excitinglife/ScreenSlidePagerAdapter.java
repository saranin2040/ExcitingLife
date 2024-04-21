package com.example.excitinglife;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 2;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // Возвращает соответствующий фрагмент для каждой позиции
        switch (position) {
            case 0:
                return new TimerFragment(); // Заменить на ваш фрагмент с таймером
            case 1:
                return new SecondScreenFragment(); // Заменить на ваш фрагмент со вторым экраном
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}

