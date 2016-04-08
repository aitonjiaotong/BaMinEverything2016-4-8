package com.aiton.bamin.changtukepiao.Cdachezuche.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class TypeCarListInfo
{


    /**
     * contains : [{"car":{"id":14,"licensePlate":"闽AYY563","model":"宝马","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"color":"白色","engineCode":"KNN-M54-GH","mileage":1.231313213E9,"maintenanceMileage":5000,"status":0,"deposit":2000,"buyDate":"2016.03.01","inspection":"2016.03.15","image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽AYY563.png","note":"宝马车","planId":1,"lei":0,"store_id":1,"licensePlateColor":null},"plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}},{"car":{"id":17,"licensePlate":"闽ALK45B","model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"color":"白色","engineCode":"KNN-M54-GH","mileage":1.2313214E7,"maintenanceMileage":5000,"status":0,"deposit":2000,"buyDate":"2016.03.01","inspection":"2016.03.15","image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽ALK45B.png","note":"奔驰车","planId":1,"lei":0,"store_id":1,"licensePlateColor":null},"plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}},{"car":{"id":18,"licensePlate":"闽AOP3LK","model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"color":"白色","engineCode":"KNN-M54-GH","mileage":1.231313132E9,"maintenanceMileage":5000,"status":0,"deposit":2000,"buyDate":"2016.03.01","inspection":"2016.03.15","image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽AOP3LK.png","note":"奔驰车","planId":1,"lei":0,"store_id":1,"licensePlateColor":null},"plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}},{"car":{"id":25,"licensePlate":"闽A1D032","model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"color":"白色","engineCode":"KNN-M54-GH","mileage":1231313,"maintenanceMileage":5000,"status":0,"deposit":2000,"buyDate":"2016.03.01","inspection":"2016.03.15","image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽A1D032.png","note":"奔驰车","planId":2,"lei":0,"store_id":1,"licensePlateColor":null},"plan":{"id":2,"name":"公务二型","price":150,"unitMileage":3000,"outMileage":9.5,"outTime":2.5,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}},{"car":{"id":34,"licensePlate":"闽D65895","model":"比亚迪","type":"甲壳虫","box":"2厢","pailiang":"1.3L","seat":3,"zidong":0,"color":"白色","engineCode":"AA-BB-CC-DD-UU","mileage":96000,"maintenanceMileage":1000,"status":0,"deposit":3000,"buyDate":"2016.01.01","inspection":"2016.01.05","image":"http://120.55.166.203:8080/bmpw/cars/闽D65895.jpg","note":"","planId":1,"lei":0,"store_id":1,"licensePlateColor":null},"plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}},{"car":{"id":35,"licensePlate":"闽C77HG6","model":"东风","type":"MM","box":"3厢","pailiang":"1.5L","seat":5,"zidong":0,"color":"红色","engineCode":"665-98-554","mileage":456000,"maintenanceMileage":2000,"status":0,"deposit":3000,"buyDate":"2016-03-17","inspection":"2016-03-25","image":"http://120.55.166.203:8080/bmpw/cars/闽C77HG6.jpg","note":"","planId":1,"lei":0,"store_id":1,"licensePlateColor":null},"plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}},{"car":{"id":36,"licensePlate":"闽D88888","model":"丰田","type":"SUV","box":"3厢","pailiang":"1.5L","seat":5,"zidong":0,"color":"绿色","engineCode":"99-66-22","mileage":100000,"maintenanceMileage":1000,"status":0,"deposit":3000,"buyDate":"2016-03-09","inspection":"2016-03-17","image":"http://120.55.166.203:8080/bmpw/cars/闽D88888.jpg","note":"","planId":1,"lei":0,"store_id":1,"licensePlateColor":null},"plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}},{"car":{"id":37,"licensePlate":"闽A88888","model":"本田","type":"LE","box":"3厢","pailiang":"1.5L","seat":5,"zidong":1,"color":"红色","engineCode":"99-22-11","mileage":65000,"maintenanceMileage":3000,"status":0,"deposit":3000,"buyDate":"2016-03-15","inspection":"2016-03-17","image":"http://120.55.166.203:8080/bmpw/cars/闽A88888.jpg","note":"","planId":1,"lei":0,"store_id":1,"licensePlateColor":null},"plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}}]
     * num : 2
     */

    private int num;
    /**
     * car : {"id":14,"licensePlate":"闽AYY563","model":"宝马","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"color":"白色","engineCode":"KNN-M54-GH","mileage":1.231313213E9,"maintenanceMileage":5000,"status":0,"deposit":2000,"buyDate":"2016.03.01","inspection":"2016.03.15","image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽AYY563.png","note":"宝马车","planId":1,"lei":0,"store_id":1,"licensePlateColor":null}
     * plan : {"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":0,"insurance":50,"hasDriver":200,"others":0,"poundage":10,"franchiseFees":100}
     */

    private List<ContainsEntity> contains;

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public List<ContainsEntity> getContains()
    {
        return contains;
    }

    public void setContains(List<ContainsEntity> contains)
    {
        this.contains = contains;
    }

    public static class ContainsEntity
    {
        /**
         * id : 14
         * licensePlate : 闽AYY563
         * model : 宝马
         * type : LK
         * box : 3厢
         * pailiang : 1.6L
         * seat : 6
         * zidong : 0
         * color : 白色
         * engineCode : KNN-M54-GH
         * mileage : 1.231313213E9
         * maintenanceMileage : 5000.0
         * status : 0
         * deposit : 2000.0
         * buyDate : 2016.03.01
         * inspection : 2016.03.15
         * image :
         * note : 宝马车
         * planId : 1
         * lei : 0
         * store_id : 1
         * licensePlateColor : null
         */

        private CarEntity car;
        /**
         * id : 1
         * name : 公务一型
         * price : 200.0
         * unitMileage : 100.0
         * outMileage : 10.0
         * outTime : 3.0
         * flag : 0
         * jijia : 0
         * insurance : 50.0
         * hasDriver : 200.0
         * others : 0.0
         * poundage : 10.0
         * franchiseFees : 100.0
         */

        private PlanEntity plan;

        public CarEntity getCar()
        {
            return car;
        }

        public void setCar(CarEntity car)
        {
            this.car = car;
        }

        public PlanEntity getPlan()
        {
            return plan;
        }

        public void setPlan(PlanEntity plan)
        {
            this.plan = plan;
        }

        public static class CarEntity implements Serializable
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
            private int store_id;
            private Object licensePlateColor;

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

            public int getStore_id()
            {
                return store_id;
            }

            public void setStore_id(int store_id)
            {
                this.store_id = store_id;
            }

            public Object getLicensePlateColor()
            {
                return licensePlateColor;
            }

            public void setLicensePlateColor(Object licensePlateColor)
            {
                this.licensePlateColor = licensePlateColor;
            }
        }

        public static class PlanEntity implements Serializable
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
}
