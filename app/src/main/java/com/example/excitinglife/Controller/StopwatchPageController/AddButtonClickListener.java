package com.example.excitinglife.Controller.StopwatchPageController;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.excitinglife.MainActivity;
import com.example.excitinglife.R;
import com.example.excitinglife.SmartLog;

public class AddButtonClickListener implements View.OnClickListener
{
    AddButtonClickListener(FragmentActivity activity, Context context,View view)
    {
        this.activity=activity;
        this.context=context;
        this.view=view;
    }

    @Override
    public void onClick(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Введи как будешь обзывать свой таймер");

        final EditText input = new EditText(activity);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Кнопка "OK", которая добавляет таймер с введенным названием
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String timerName = input.getText().toString();
                if (!timerName.isEmpty()) {
                    Integer id = MainActivity.getBc().addStopwatch(timerName);
                    MainActivity.getVisual().paintNewTimer(id,MainActivity.getBc().getStopwatchs().get(id).getName(),view,context);

                    SmartLog.logUseful("New stopwatch " + id + " with name " + timerName);
                } else {
                    Toast.makeText(activity, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                SmartLog.logUseful("Отмена подписывания договора дьявола");
                dialog.cancel();
            }
        });

        builder.show();
    }

    private FragmentActivity activity;
    private Context context;
    private View view;
}
