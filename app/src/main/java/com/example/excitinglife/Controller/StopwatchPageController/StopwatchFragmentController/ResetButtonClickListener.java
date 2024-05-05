package com.example.excitinglife.Controller.StopwatchPageController.StopwatchFragmentController;

import android.view.View;

import com.example.excitinglife.MainActivity;
import com.example.excitinglife.SmartLog;

public class ResetButtonClickListener implements View.OnClickListener
{
    public ResetButtonClickListener(int id)
    {
        this.id=id;
    }
    @Override
    public void onClick(View v)
    {
        MainActivity.getBc().resetStopwatch(id);
        MainActivity.getVisual().paintBeginStopwatchState(id);
        MainActivity.getVisual().updateTextTimer(id,0, 0, 0, 0, 0);

        SmartLog.logUseful("Stopwatch " + MainActivity.getBc().getStopwatchs().get(id).getName() + " reset");
    }

    private int id;
}
