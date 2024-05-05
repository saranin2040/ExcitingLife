package com.example.excitinglife.Controller.StopwatchPageController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.excitinglife.SmartLog;
import com.example.excitinglife.MainActivity;
import com.example.excitinglife.R;


public class StopwatchPageController extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        loadStopWatch(view);

        Button addTimerButton = view.findViewById(R.id.addTimerButton);
        addTimerButton.setOnClickListener(new AddButtonClickListener(getActivity(),getContext(),view));

        return view;
    }

    private void loadStopWatch(View view)
    {
        for (Integer stopwatchId : MainActivity.bc.get(0).getStopwatchs().keySet())
        {
            MainActivity.getVisual().paintNewTimer(stopwatchId,MainActivity.getBc().getStopwatchs().get(stopwatchId).getName(),view,getContext());
            SmartLog.logUseful("Load stopwatch "+stopwatchId);
        }
    }
}
