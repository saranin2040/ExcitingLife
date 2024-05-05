package com.example.excitinglife.Controller.StopwatchPageController.StopwatchFragmentController;

import android.view.View;
import android.widget.LinearLayout;

import com.example.excitinglife.MainActivity;
import com.example.excitinglife.SmartLog;

public class CloseButtonClickListener implements View.OnClickListener{

    public CloseButtonClickListener(int id)
    {
        this.id=id;
    }

    @Override
    public void onClick(View v)
    {
        MainActivity.getBc().removeStopwatch(id);
        MainActivity.getVisual().clearStopwatch(id);
        SmartLog.logUseful("Stopwatch " + id + " removed");
    }

    private int id;
}
