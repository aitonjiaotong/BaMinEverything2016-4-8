package com.aiton.bamin.changtukepiao.Cdachezuche.models;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/22.
 */
public class ChooseFristInfo implements Serializable
{
    private String unitOfAccount;
    private String cityName;
    private long getCarTime;
    private long returnCarTime;
    private int hasDriver;
    private int driverID;
    private int carType;
    private int carID;
    private TypeCarListInfo.ContainsEntity.CarEntity car;
    private TypeCarListInfo.ContainsEntity.PlanEntity plan;

    public ChooseFristInfo(String unitOfAccount, String cityName, long getCarTime, long returnCarTime, int hasDriver, int driverID, int carType, int carID,TypeCarListInfo.ContainsEntity.CarEntity car,TypeCarListInfo.ContainsEntity.PlanEntity plan)
    {
        this.unitOfAccount = unitOfAccount;
        this.cityName = cityName;
        this.getCarTime = getCarTime;
        this.returnCarTime = returnCarTime;
        this.hasDriver = hasDriver;
        this.driverID = driverID;
        this.carType = carType;
        this.carID = carID;
        this.car = car;
        this.plan = plan;
    }

    public String getUnitOfAccount()
    {
        return unitOfAccount;
    }

    public void setUnitOfAccount(String unitOfAccount)
    {
        this.unitOfAccount = unitOfAccount;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public long getGetCarTime()
    {
        return getCarTime;
    }

    public void setGetCarTime(long getCarTime)
    {
        this.getCarTime = getCarTime;
    }

    public long getReturnCarTime()
    {
        return returnCarTime;
    }

    public void setReturnCarTime(long returnCarTime)
    {
        this.returnCarTime = returnCarTime;
    }

    public int getHasDriver()
    {
        return hasDriver;
    }

    public void setHasDriver(int hasDriver)
    {
        this.hasDriver = hasDriver;
    }

    public int getDriverID()
    {
        return driverID;
    }

    public void setDriverID(int driverID)
    {
        this.driverID = driverID;
    }

    public int getCarType()
    {
        return carType;
    }

    public void setCarType(int carType)
    {
        this.carType = carType;
    }

    public int getCarID()
    {
        return carID;
    }

    public void setCarID(int carID)
    {
        this.carID = carID;
    }

    public TypeCarListInfo.ContainsEntity.CarEntity getCar()
    {
        return car;
    }

    public void setCar(TypeCarListInfo.ContainsEntity.CarEntity car)
    {
        this.car = car;
    }

    public TypeCarListInfo.ContainsEntity.PlanEntity getPlan()
    {
        return plan;
    }

    public void setPlan(TypeCarListInfo.ContainsEntity.PlanEntity plan)
    {
        this.plan = plan;
    }
}
