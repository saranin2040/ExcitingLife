package com.example.excitinglife.BusinessLogic;

import com.example.excitinglife.BusinessLogic.Commodity.Commodity;
import com.example.excitinglife.BusinessLogic.Commodity.Fun;
import com.example.excitinglife.BusinessLogic.Stopwatch.Stopwatch;
import com.example.excitinglife.BusinessLogic.Stopwatch.StopwatchDo;
import com.example.excitinglife.BusinessLogic.Task.Task;
import com.example.excitinglife.BusinessLogic.Task.TaskDo;

import java.util.HashMap;

public class BusinessLogic
{
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

    public long getTimeStopwatchs(Integer stopwatchId)
    {
        return stopwatchs.get(stopwatchId).getTime();
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

    public void addStopwatch(String name)
    {
        stopwatchs.put(getNextStopwatchId(),new StopwatchDo(name));
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

    public void startStopwatch(Integer taskId)
    {
        StopwatchDo stopwatch= (StopwatchDo) stopwatchs.get(taskId);
        stopwatch.start();
    }

    public void stopStopwatch(Integer taskId)
    {
        StopwatchDo stopwatch= (StopwatchDo) stopwatchs.get(taskId);
        stopwatch.stop();
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



    private String nameUser = "user1";
    private int score=0;

    private Integer nextTaskId=0;
    private Integer nextCommidityId=0;
    private Integer nextStopwatchId=0;

    private HashMap<Integer,Task> tasks=new HashMap<Integer, Task>();
    private HashMap<Integer,Commodity> commodities=new HashMap<Integer, Commodity>();
    private HashMap<Integer, Stopwatch> stopwatchs=new HashMap<Integer, Stopwatch>();
}
