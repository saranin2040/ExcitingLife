package com.example.excitinglife.BusinessLogic.Task;

public class TaskDo implements Task{


    public TaskDo(String name, int reward)
    {
        this.name=name;
        this.reward=reward;
    }

    public String getName()
    {
        return name;
    }
    public int getReward()
    {
        return reward;
    }

    public void complete()
    {

    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    private String name=null;
    private int reward=0;
    private boolean animated=false;
}
