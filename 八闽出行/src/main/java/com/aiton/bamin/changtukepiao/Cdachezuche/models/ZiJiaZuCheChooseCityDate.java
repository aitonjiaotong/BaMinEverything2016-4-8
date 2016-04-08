package com.aiton.bamin.changtukepiao.Cdachezuche.models;

import java.io.Serializable;

/**
 * Created by zjb on 2016/3/28.
 */
public class ZiJiaZuCheChooseCityDate implements Serializable {
    private long zuchuDate; //租出时间
    private long planReturnDate;//计划还车时间
    private int getCar;//取车地点
    private int returnCar;//还车地点
    private String takeCarStore;//取车门店
    private String returnCarStore;//换车门店
    private int dayCounts;
    private String mStartDate;
    private String mStartTime;
    private String mEndDate;
    private String mEndTime;

    public long getZuchuDate() {
        return zuchuDate;
    }

    public void setZuchuDate(long zuchuDate) {
        this.zuchuDate = zuchuDate;
    }

    public long getPlanReturnDate() {
        return planReturnDate;
    }

    public void setPlanReturnDate(long planReturnDate) {
        this.planReturnDate = planReturnDate;
    }

    public int getGetCar() {
        return getCar;
    }

    public void setGetCar(int getCar) {
        this.getCar = getCar;
    }

    public int getReturnCar() {
        return returnCar;
    }

    public void setReturnCar(int returnCar) {
        this.returnCar = returnCar;
    }

    public String getTakeCarStore() {
        return takeCarStore;
    }

    public void setTakeCarStore(String takeCarStore) {
        this.takeCarStore = takeCarStore;
    }

    public String getReturnCarStore() {
        return returnCarStore;
    }

    public void setReturnCarStore(String returnCarStore) {
        this.returnCarStore = returnCarStore;
    }

    public int getDayCounts() {
        return dayCounts;
    }

    public void setDayCounts(int dayCounts) {
        this.dayCounts = dayCounts;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }

    public ZiJiaZuCheChooseCityDate(long zuchuDate, long planReturnDate, int getCar, int returnCar, String takeCarStore, String returnCarStore, int dayCounts, String startDate, String startTime, String endDate, String endTime) {
        this.zuchuDate = zuchuDate;
        this.planReturnDate = planReturnDate;
        this.getCar = getCar;
        this.returnCar = returnCar;
        this.takeCarStore = takeCarStore;
        this.returnCarStore = returnCarStore;
        this.dayCounts = dayCounts;
        mStartDate = startDate;
        mStartTime = startTime;
        mEndDate = endDate;
        mEndTime = endTime;
    }
}
