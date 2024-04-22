package com.example.excitinglife.BusinessLogic.Commodity;

public class Fun implements Commodity
{
    public Fun(String name, int cost)
    {
        this.name=name;
        this.cost=cost;
    }

    public String getName()
    {
        return name;
    }
    public int getCost()
    {
        return cost;
    }

    private String name=null;
    private int cost=0;
}
