package com.aiton.bamin.changtukepiao.Cdachezuche.models;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/30.
 */
public class OrderDetailsInfo
{

    /**
     * id : 90
     * carId : 18
     * planId : 1
     * zuchuDate : 1459325194000
     * huancheDate : null
     * planReturnDate : 1467101194000
     * limitMileage : null
     * accountId : 5
     * guarantorId : null
     * beforeMileage : 98744.0
     * afterMileage : null
     * jijiatime : null
     * timePrice : null
     * outMileagePrice : null
     * outTimePrice : null
     * zuPrice : null
     * shouyajin : null
     * price : 0.0
     * note : null
     * flag : 0
     * driverId : 2
     * hasDriver : 0
     * getCar : 1
     * returnCar : 1
     * advancePayment : null
     * date : 1459317996000
     * status : 0
     * sale : null
     * institutionsCode : 1458290971239
     * hasFranchiseFees : null
     */

    private OrderEntity order;
    /**
     * id : 18
     * licensePlate : 闽AOP3LK
     * model : 奔驰
     * type : LK
     * box : 3厢
     * pailiang : 1.6L
     * seat : 6
     * zidong : 0
     * color : 白色
     * engineCode : KNN-M54-GH
     * mileage : 98744.0
     * maintenanceMileage : 5000.0
     * status : 1
     * deposit : 2000.0
     * buyDate : 2016.03.01
     * inspection : 2016.03.15
     * image :
     * note : 奔驰车
     * planId : 1
     * lei : 0
     * store_id : null
     */

    private CarEntity car;
    /**
     * id : 1
     * name : 三明客运中心店
     * address : aaaa
     * city : 三明
     * phone : 12345678910
     * head : cherry
     * longitude : 26.264016
     * latitude : 117.640016
     */

    private GetCarStoreEntity getCarStore;
    /**
     * id : 1
     * name : 三明客运中心店
     * address : aaaa
     * city : 三明
     * phone : 12345678910
     * head : cherry
     * longitude : 26.264016
     * latitude : 117.640016
     */

    private ReturnStoreEntity returnStore;
    /**
     * id : 2
     * name : 傻
     * phone : 16777663878
     * idcard : nfkjhasijhbdsa
     * sex : 男
     * drivingYear : 8
     * image : null
     * star : 4.0
     * status : 0
     */

    private DriverEntity driver;
    /**
     * id : 1
     * name : 公务一型
     * price : 200.0
     * unitMileage : 100.0
     * outMileage : 10.0
     * outTime : 3.0
     * flag : 0
     * jijia : null
     * insurance : null
     * hasDriver : null
     * others : null
     * poundage : null
     * franchiseFees : null
     */

    private PlanEntity plan;

    public OrderEntity getOrder()
    {
        return order;
    }

    public void setOrder(OrderEntity order)
    {
        this.order = order;
    }

    public CarEntity getCar()
    {
        return car;
    }

    public void setCar(CarEntity car)
    {
        this.car = car;
    }

    public GetCarStoreEntity getGetCarStore()
    {
        return getCarStore;
    }

    public void setGetCarStore(GetCarStoreEntity getCarStore)
    {
        this.getCarStore = getCarStore;
    }

    public ReturnStoreEntity getReturnStore()
    {
        return returnStore;
    }

    public void setReturnStore(ReturnStoreEntity returnStore)
    {
        this.returnStore = returnStore;
    }

    public DriverEntity getDriver()
    {
        return driver;
    }

    public void setDriver(DriverEntity driver)
    {
        this.driver = driver;
    }

    public PlanEntity getPlan()
    {
        return plan;
    }

    public void setPlan(PlanEntity plan)
    {
        this.plan = plan;
    }

    public static class OrderEntity
    {
        private int id;
        private int carId;
        private int planId;
        private long zuchuDate;
        private Object huancheDate;
        private long planReturnDate;
        private Object limitMileage;
        private int accountId;
        private Object guarantorId;
        private double beforeMileage;
        private Object afterMileage;
        private Object jijiatime;
        private Object timePrice;
        private Object outMileagePrice;
        private Object outTimePrice;
        private Object zuPrice;
        private Object shouyajin;
        private double price;
        private Object note;
        private int flag;
        private int driverId;
        private int hasDriver;
        private int getCar;
        private int returnCar;
        private Object advancePayment;
        private long date;
        private int status;
        private Object sale;
        private String institutionsCode;
        private Object hasFranchiseFees;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public int getCarId()
        {
            return carId;
        }

        public void setCarId(int carId)
        {
            this.carId = carId;
        }

        public int getPlanId()
        {
            return planId;
        }

        public void setPlanId(int planId)
        {
            this.planId = planId;
        }

        public long getZuchuDate()
        {
            return zuchuDate;
        }

        public void setZuchuDate(long zuchuDate)
        {
            this.zuchuDate = zuchuDate;
        }

        public Object getHuancheDate()
        {
            return huancheDate;
        }

        public void setHuancheDate(Object huancheDate)
        {
            this.huancheDate = huancheDate;
        }

        public long getPlanReturnDate()
        {
            return planReturnDate;
        }

        public void setPlanReturnDate(long planReturnDate)
        {
            this.planReturnDate = planReturnDate;
        }

        public Object getLimitMileage()
        {
            return limitMileage;
        }

        public void setLimitMileage(Object limitMileage)
        {
            this.limitMileage = limitMileage;
        }

        public int getAccountId()
        {
            return accountId;
        }

        public void setAccountId(int accountId)
        {
            this.accountId = accountId;
        }

        public Object getGuarantorId()
        {
            return guarantorId;
        }

        public void setGuarantorId(Object guarantorId)
        {
            this.guarantorId = guarantorId;
        }

        public double getBeforeMileage()
        {
            return beforeMileage;
        }

        public void setBeforeMileage(double beforeMileage)
        {
            this.beforeMileage = beforeMileage;
        }

        public Object getAfterMileage()
        {
            return afterMileage;
        }

        public void setAfterMileage(Object afterMileage)
        {
            this.afterMileage = afterMileage;
        }

        public Object getJijiatime()
        {
            return jijiatime;
        }

        public void setJijiatime(Object jijiatime)
        {
            this.jijiatime = jijiatime;
        }

        public Object getTimePrice()
        {
            return timePrice;
        }

        public void setTimePrice(Object timePrice)
        {
            this.timePrice = timePrice;
        }

        public Object getOutMileagePrice()
        {
            return outMileagePrice;
        }

        public void setOutMileagePrice(Object outMileagePrice)
        {
            this.outMileagePrice = outMileagePrice;
        }

        public Object getOutTimePrice()
        {
            return outTimePrice;
        }

        public void setOutTimePrice(Object outTimePrice)
        {
            this.outTimePrice = outTimePrice;
        }

        public Object getZuPrice()
        {
            return zuPrice;
        }

        public void setZuPrice(Object zuPrice)
        {
            this.zuPrice = zuPrice;
        }

        public Object getShouyajin()
        {
            return shouyajin;
        }

        public void setShouyajin(Object shouyajin)
        {
            this.shouyajin = shouyajin;
        }

        public double getPrice()
        {
            return price;
        }

        public void setPrice(double price)
        {
            this.price = price;
        }

        public Object getNote()
        {
            return note;
        }

        public void setNote(Object note)
        {
            this.note = note;
        }

        public int getFlag()
        {
            return flag;
        }

        public void setFlag(int flag)
        {
            this.flag = flag;
        }

        public int getDriverId()
        {
            return driverId;
        }

        public void setDriverId(int driverId)
        {
            this.driverId = driverId;
        }

        public int getHasDriver()
        {
            return hasDriver;
        }

        public void setHasDriver(int hasDriver)
        {
            this.hasDriver = hasDriver;
        }

        public int getGetCar()
        {
            return getCar;
        }

        public void setGetCar(int getCar)
        {
            this.getCar = getCar;
        }

        public int getReturnCar()
        {
            return returnCar;
        }

        public void setReturnCar(int returnCar)
        {
            this.returnCar = returnCar;
        }

        public Object getAdvancePayment()
        {
            return advancePayment;
        }

        public void setAdvancePayment(Object advancePayment)
        {
            this.advancePayment = advancePayment;
        }

        public long getDate()
        {
            return date;
        }

        public void setDate(long date)
        {
            this.date = date;
        }

        public int getStatus()
        {
            return status;
        }

        public void setStatus(int status)
        {
            this.status = status;
        }

        public Object getSale()
        {
            return sale;
        }

        public void setSale(Object sale)
        {
            this.sale = sale;
        }

        public String getInstitutionsCode()
        {
            return institutionsCode;
        }

        public void setInstitutionsCode(String institutionsCode)
        {
            this.institutionsCode = institutionsCode;
        }

        public Object getHasFranchiseFees()
        {
            return hasFranchiseFees;
        }

        public void setHasFranchiseFees(Object hasFranchiseFees)
        {
            this.hasFranchiseFees = hasFranchiseFees;
        }
    }

    public static class CarEntity
    {
        private int id;
        private String licensePlate;
        private String model;
        private String type;
        private String box;
        private String pailiang;
        private int seat;
        private int zidong;
        private String color;
        private String engineCode;
        private double mileage;
        private double maintenanceMileage;
        private int status;
        private double deposit;
        private String buyDate;
        private String inspection;
        private String image;
        private String note;
        private int planId;
        private int lei;
        private Object store_id;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getLicensePlate()
        {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
        }

        public String getModel()
        {
            return model;
        }

        public void setModel(String model)
        {
            this.model = model;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public String getBox()
        {
            return box;
        }

        public void setBox(String box)
        {
            this.box = box;
        }

        public String getPailiang()
        {
            return pailiang;
        }

        public void setPailiang(String pailiang)
        {
            this.pailiang = pailiang;
        }

        public int getSeat()
        {
            return seat;
        }

        public void setSeat(int seat)
        {
            this.seat = seat;
        }

        public int getZidong()
        {
            return zidong;
        }

        public void setZidong(int zidong)
        {
            this.zidong = zidong;
        }

        public String getColor()
        {
            return color;
        }

        public void setColor(String color)
        {
            this.color = color;
        }

        public String getEngineCode()
        {
            return engineCode;
        }

        public void setEngineCode(String engineCode)
        {
            this.engineCode = engineCode;
        }

        public double getMileage()
        {
            return mileage;
        }

        public void setMileage(double mileage)
        {
            this.mileage = mileage;
        }

        public double getMaintenanceMileage()
        {
            return maintenanceMileage;
        }

        public void setMaintenanceMileage(double maintenanceMileage)
        {
            this.maintenanceMileage = maintenanceMileage;
        }

        public int getStatus()
        {
            return status;
        }

        public void setStatus(int status)
        {
            this.status = status;
        }

        public double getDeposit()
        {
            return deposit;
        }

        public void setDeposit(double deposit)
        {
            this.deposit = deposit;
        }

        public String getBuyDate()
        {
            return buyDate;
        }

        public void setBuyDate(String buyDate)
        {
            this.buyDate = buyDate;
        }

        public String getInspection()
        {
            return inspection;
        }

        public void setInspection(String inspection)
        {
            this.inspection = inspection;
        }

        public String getImage()
        {
            return image;
        }

        public void setImage(String image)
        {
            this.image = image;
        }

        public String getNote()
        {
            return note;
        }

        public void setNote(String note)
        {
            this.note = note;
        }

        public int getPlanId()
        {
            return planId;
        }

        public void setPlanId(int planId)
        {
            this.planId = planId;
        }

        public int getLei()
        {
            return lei;
        }

        public void setLei(int lei)
        {
            this.lei = lei;
        }

        public Object getStore_id()
        {
            return store_id;
        }

        public void setStore_id(Object store_id)
        {
            this.store_id = store_id;
        }
    }

    public static class GetCarStoreEntity
    {
        private int id;
        private String name;
        private String address;
        private String city;
        private String phone;
        private String head;
        private double longitude;
        private double latitude;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public String getCity()
        {
            return city;
        }

        public void setCity(String city)
        {
            this.city = city;
        }

        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }

        public String getHead()
        {
            return head;
        }

        public void setHead(String head)
        {
            this.head = head;
        }

        public double getLongitude()
        {
            return longitude;
        }

        public void setLongitude(double longitude)
        {
            this.longitude = longitude;
        }

        public double getLatitude()
        {
            return latitude;
        }

        public void setLatitude(double latitude)
        {
            this.latitude = latitude;
        }
    }

    public static class ReturnStoreEntity
    {
        private int id;
        private String name;
        private String address;
        private String city;
        private String phone;
        private String head;
        private double longitude;
        private double latitude;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public String getCity()
        {
            return city;
        }

        public void setCity(String city)
        {
            this.city = city;
        }

        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }

        public String getHead()
        {
            return head;
        }

        public void setHead(String head)
        {
            this.head = head;
        }

        public double getLongitude()
        {
            return longitude;
        }

        public void setLongitude(double longitude)
        {
            this.longitude = longitude;
        }

        public double getLatitude()
        {
            return latitude;
        }

        public void setLatitude(double latitude)
        {
            this.latitude = latitude;
        }
    }

    public static class DriverEntity implements Serializable
    {
        private int id;
        private String name;
        private String phone;
        private String idcard;
        private String sex;
        private int drivingYear;
        private String image;
        private double star;
        private int status;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }

        public String getIdcard()
        {
            return idcard;
        }

        public void setIdcard(String idcard)
        {
            this.idcard = idcard;
        }

        public String getSex()
        {
            return sex;
        }

        public void setSex(String sex)
        {
            this.sex = sex;
        }

        public int getDrivingYear()
        {
            return drivingYear;
        }

        public void setDrivingYear(int drivingYear)
        {
            this.drivingYear = drivingYear;
        }

        public String getImage()
        {
            return image;
        }

        public void setImage(String image)
        {
            this.image = image;
        }

        public double getStar()
        {
            return star;
        }

        public void setStar(double star)
        {
            this.star = star;
        }

        public int getStatus()
        {
            return status;
        }

        public void setStatus(int status)
        {
            this.status = status;
        }
    }

    public static class PlanEntity
    {
        private int id;
        private String name;
        private double price;
        private double unitMileage;
        private double outMileage;
        private double outTime;
        private int flag;
        private int jijia;
        private double insurance;
        private double hasDriver;
        private double others;
        private double poundage;
        private double franchiseFees;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public double getPrice()
        {
            return price;
        }

        public void setPrice(double price)
        {
            this.price = price;
        }

        public double getUnitMileage()
        {
            return unitMileage;
        }

        public void setUnitMileage(double unitMileage)
        {
            this.unitMileage = unitMileage;
        }

        public double getOutMileage()
        {
            return outMileage;
        }

        public void setOutMileage(double outMileage)
        {
            this.outMileage = outMileage;
        }

        public double getOutTime()
        {
            return outTime;
        }

        public void setOutTime(double outTime)
        {
            this.outTime = outTime;
        }

        public int getFlag()
        {
            return flag;
        }

        public void setFlag(int flag)
        {
            this.flag = flag;
        }

        public int getJijia()
        {
            return jijia;
        }

        public void setJijia(int jijia)
        {
            this.jijia = jijia;
        }

        public double getInsurance()
        {
            return insurance;
        }

        public void setInsurance(double insurance)
        {
            this.insurance = insurance;
        }

        public double getHasDriver()
        {
            return hasDriver;
        }

        public void setHasDriver(double hasDriver)
        {
            this.hasDriver = hasDriver;
        }

        public double getOthers()
        {
            return others;
        }

        public void setOthers(double others)
        {
            this.others = others;
        }

        public double getPoundage()
        {
            return poundage;
        }

        public void setPoundage(double poundage)
        {
            this.poundage = poundage;
        }

        public double getFranchiseFees()
        {
            return franchiseFees;
        }

        public void setFranchiseFees(double franchiseFees)
        {
            this.franchiseFees = franchiseFees;
        }
    }
}
