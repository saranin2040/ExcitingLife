package com.example.excitinglife.BusinessLogic;

import android.util.Log;

import com.example.excitinglife.BusinessLogic.Commodity.Commodity;
import com.example.excitinglife.BusinessLogic.Commodity.Fun;
import com.example.excitinglife.BusinessLogic.Stopwatch.Stopwatch;
import com.example.excitinglife.BusinessLogic.Stopwatch.StopwatchDo;
import com.example.excitinglife.BusinessLogic.Task.Task;
import com.example.excitinglife.BusinessLogic.Task.TaskDo;
import com.example.excitinglife.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;

//Привет любимый Дмитрий Ерошенко!

public class BusinessLogic
{

    public BusinessLogic(File directory)
    {
        this.directory = directory;
        loadStopwatches();
        if (stopwatchs.isEmpty()) {
            addStopwatch("time your stupid");
        }
        else {
            nextStopwatchId= Collections.max(stopwatchs.keySet())+1;
        }



        long elapsedTime = getStopwatchs().get(0).getElapsedTime();

        int days = (int) (elapsedTime / (1000 * 60 * 60 * 24));
        int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
        int seconds = (int) ((elapsedTime / 1000) % 60);
        int milliseconds = (int) (elapsedTime % 1000);
        milliseconds = milliseconds / 10;


        //timerTextView.setText(String.format("%d:%02d:%02d:%02d:%02d", days, hours, minutes, seconds, milliseconds));

    }
    //get
    public int getScore()
    {
        return score;
    }

    public HashMap<Integer,Task> getTasks()
    {
        return tasks;
    }

    public HashMap<Integer,Commodity> getCommodities()
    {
        return commodities;
    }
    public HashMap<Integer,Stopwatch> getStopwatchs()
    {
        return stopwatchs;
    }


    //add
    public void addTask(String name, int reward)
    {
        tasks.put(getNextTaskId(),new TaskDo(name,reward));
    }

    public void addCommodity(String name, int cost)
    {
        commodities.put(getNextCommidityId(),new Fun(name,cost));
    }

    public Integer addStopwatch(String name)
    {
        Integer newStopwatchId = getNextStopwatchId();
        stopwatchs.put(newStopwatchId,new StopwatchDo(name));
        return newStopwatchId;
    }


    //remove
    public void removeTask(Integer taskId) {
        tasks.remove(taskId);
    }

    public void removeCommodity(Integer commodityId) {
        commodities.remove(commodityId);
    }

    public void removeStopwatch(Integer stopwatchId) {
        stopwatchs.remove(stopwatchId);
    }


    //action
    public boolean buy(Integer commodityId)
    {
        Commodity commodity =commodities.get(commodityId);

        reduceScore(commodity.getCost());

        return true;
    }

    public boolean completeTask(Integer taskId)
    {
        TaskDo task =(TaskDo) tasks.get(taskId);

        task.complete();
        increaseScore(task.getReward());

        return true;
    }

    public void startStopwatch(Integer stopwatchId)
    {
        StopwatchDo stopwatch= (StopwatchDo) stopwatchs.get(stopwatchId);
        stopwatch.start();
    }

    public void stopStopwatch(Integer stopwatchId)
    {
        Log.d("saranin","bc stop stopwatch");
        StopwatchDo stopwatch= (StopwatchDo) stopwatchs.get(stopwatchId);
        stopwatch.stop();
        //saveStopwatches();
    }



    private void increaseScore(int bonus)
    {
        score += bonus;
    }

    private void reduceScore(int penalty)
    {
        score += penalty;
    }


    private Integer getNextTaskId()
    {
        Integer tmp=nextTaskId;//TODO если ошибка, то здесь
        nextTaskId++;

        return tmp;//TODO если переполниться, проверку добавить
    }



    private Integer getNextCommidityId()
    {
        Integer tmp=nextCommidityId;//TODO если ошибка, то здесь
        nextCommidityId++;

        return tmp;//TODO если переполниться, проверку добавить
    }

    private Integer getNextStopwatchId()
    {
        Integer tmp=nextStopwatchId;//TODO если ошибка, то здесь
        nextStopwatchId++;

        return tmp;//TODO если переполниться, проверку добавить
    }


    public void saveStopwatches() {
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(directory, "stopwatches.ser"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(stopwatchs);
            out.close();
            fileOut.close();
            Log.d("saranin", "Stopwatches saved successfully. size = "+stopwatchs.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Функция загрузки stopwatchs из файла
    public void loadStopwatches() {
        Log.d("saranin", "loading...");
        try {
            FileInputStream fileIn = new FileInputStream(new File(directory, "stopwatches.ser"));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            stopwatchs = (HashMap<Integer, Stopwatch>) in.readObject();
            in.close();
            fileIn.close();
            Log.d("saranin", "Stopwatches loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private String nameUser = "user1";
    private int score=0;

    private Integer nextTaskId=0;
    private Integer nextCommidityId=0;
    private Integer nextStopwatchId=0;

    private HashMap<Integer,Task> tasks=new HashMap<Integer, Task>();
    private HashMap<Integer,Commodity> commodities=new HashMap<Integer, Commodity>();
    private HashMap<Integer, Stopwatch> stopwatchs=new HashMap<Integer, Stopwatch>();

    private File directory;
}
