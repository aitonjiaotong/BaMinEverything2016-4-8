package com.aiton.bamin.changtukepiao.Cdachezuche.models;

import java.io.Serializable;

/**
 * Created by zjb on 2016/3/28.
 */
public class ZiJiaZuCheOrderDetial implements Serializable{
    private int id;
    private int carId;//车辆编号
    private int planId;     //租赁计划id(防止车辆绑定plan更改,或者不使用车辆绑定plan)
    private long zuchuDate; //租出时间
    private long huancheDate; //还车时间
    private long planReturnDate;//计划还车时间
    private double limitMileage;//限制里程数
    private int accountId;//租车人id
    private int guarantorId;//担保人id
    private double beforeMileage;//开始里程数
    private double afterMileage;//结束里程数
    private int jijiatime;//计价时间
    private double timePrice;//计时租金
    private double outMileagePrice;//超程租金
    private double outTimePrice;//超时租金
    private double zuPrice;//应收租金
    private double shouyajin;//实收押金
    private double price;//总价
    private String note;//备注
    private int flag;//订单状态 0:进行中 1：完成 2:取消 3：等待结算(已还车)
    private int driverId;
    private int hasDriver;//是否带司机 0:带司机 1：不带司机
    private int getCar;//取车地点
    private int returnCar;//还车地点
    private double advancePayment;//预付款
    private long date;//下单时间
    private int status;//0：企业租车 1；个人租车
    private String sale;//收款人账号
    private String institutionsCode;//企业账号
    private int hasFranchiseFees;//是否包含不计免赔费用

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public long getZuchuDate() {
        return zuchuDate;
    }

    public void setZuchuDate(long zuchuDate) {
        this.zuchuDate = zuchuDate;
    }

    public long getHuancheDate() {
        return huancheDate;
    }

    public void setHuancheDate(long huancheDate) {
        this.huancheDate = huancheDate;
    }

    public long getPlanReturnDate() {
        return planReturnDate;
    }

    public void setPlanReturnDate(long planReturnDate) {
        this.planReturnDate = planReturnDate;
    }

    public double getLimitMileage() {
        return limitMileage;
    }

    public void setLimitMileage(double limitMileage) {
        this.limitMileage = limitMileage;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getGuarantorId() {
        return guarantorId;
    }

    public void setGuarantorId(int guarantorId) {
        this.guarantorId = guarantorId;
    }

    public double getBeforeMileage() {
        return beforeMileage;
    }

    public void setBeforeMileage(double beforeMileage) {
        this.beforeMileage = beforeMileage;
    }

    public double getAfterMileage() {
        return afterMileage;
    }

    public void setAfterMileage(double afterMileage) {
        this.afterMileage = afterMileage;
    }

    public int getJijiatime() {
        return jijiatime;
    }

    public void setJijiatime(int jijiatime) {
        this.jijiatime = jijiatime;
    }

    public double getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(double timePrice) {
        this.timePrice = timePrice;
    }

    public double getOutMileagePrice() {
        return outMileagePrice;
    }

    public void setOutMileagePrice(double outMileagePrice) {
        this.outMileagePrice = outMileagePrice;
    }

    public double getOutTimePrice() {
        return outTimePrice;
    }

    public void setOutTimePrice(double outTimePrice) {
        this.outTimePrice = outTimePrice;
    }

    public double getZuPrice() {
        return zuPrice;
    }

    public void setZuPrice(double zuPrice) {
        this.zuPrice = zuPrice;
    }

    public double getShouyajin() {
        return shouyajin;
    }

    public void setShouyajin(double shouyajin) {
        this.shouyajin = shouyajin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getHasDriver() {
        return hasDriver;
    }

    public void setHasDriver(int hasDriver) {
        this.hasDriver = hasDriver;
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

    public double getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(double advancePayment) {
        this.advancePayment = advancePayment;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getInstitutionsCode() {
        return institutionsCode;
    }

    public void setInstitutionsCode(String institutionsCode) {
        this.institutionsCode = institutionsCode;
    }

    public int getHasFranchiseFees() {
        return hasFranchiseFees;
    }

    public void setHasFranchiseFees(int hasFranchiseFees) {
        this.hasFranchiseFees = hasFranchiseFees;
    }

    public ZiJiaZuCheOrderDetial(int id, int carId, int planId, long zuchuDate, long huancheDate, long planReturnDate, double limitMileage, int accountId, int guarantorId, double beforeMileage, double afterMileage, int jijiatime, double timePrice, double outMileagePrice, double outTimePrice, double zuPrice, double shouyajin, double price, String note, int flag, int driverId, int hasDriver, int getCar, int returnCar, double advancePayment, long date, int status, String sale, String institutionsCode, int hasFranchiseFees) {
        this.id = id;
        this.carId = carId;
        this.planId = planId;
        this.zuchuDate = zuchuDate;
        this.huancheDate = huancheDate;
        this.planReturnDate = planReturnDate;
        this.limitMileage = limitMileage;
        this.accountId = accountId;
        this.guarantorId = guarantorId;
        this.beforeMileage = beforeMileage;
        this.afterMileage = afterMileage;
        this.jijiatime = jijiatime;
        this.timePrice = timePrice;
        this.outMileagePrice = outMileagePrice;
        this.outTimePrice = outTimePrice;
        this.zuPrice = zuPrice;
        this.shouyajin = shouyajin;
        this.price = price;
        this.note = note;
        this.flag = flag;
        this.driverId = driverId;
        this.hasDriver = hasDriver;
        this.getCar = getCar;
        this.returnCar = returnCar;
        this.advancePayment = advancePayment;
        this.date = date;
        this.status = status;
        this.sale = sale;
        this.institutionsCode = institutionsCode;
        this.hasFranchiseFees = hasFranchiseFees;
    }
}
