package com.aiton.bamin.changtukepiao.Cdachezuche.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zjb on 2016/3/19.
 */
public class CarInfoList implements Serializable{

    /**
     * contains : [{"model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"plan_id":1,"image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽AU0056.png","plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}},{"model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"plan_id":1,"image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽ALK45B.png","plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}},{"model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"plan_id":1,"image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽AOP3LK.png","plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}},{"model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"plan_id":1,"image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽A3P7LK.png","plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}},{"model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"plan_id":1,"image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽A3TVLK.png","plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}},{"model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"plan_id":1,"image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽AED01S.png","plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}},{"model":"奔驰","type":"IN","box":"3厢","pailiang":"1.5L","seat":5,"zidong":0,"plan_id":1,"image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽D65418.jpg","plan":{"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}},{"model":"奔驰","type":"LK","box":"3厢","pailiang":"1.6L","seat":6,"zidong":0,"plan_id":2,"image":"C:\\Users\\user\\IdeaProjects\\bmpw\\target\\bmpw\\WEB-INF\\pages\\cars\\闽AED032.png","plan":{"id":2,"name":"公务二型","price":150,"unitMileage":3000,"outMileage":9.5,"outTime":2.5,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}}]
     * num : 2
     */

    private int num;
    /**
     * model : 奔驰
     * type : LK
     * box : 3厢
     * pailiang : 1.6L
     * seat : 6
     * zidong : 0
     * plan_id : 1
     * image : null
     * plan : {"id":1,"name":"公务一型","price":200,"unitMileage":100,"outMileage":10,"outTime":3,"flag":0,"jijia":null,"insurance":null,"hasDriver":null,"others":null,"poundage":null,"franchiseFees":null}
     */

    private List<ContainsEntity> contains;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<ContainsEntity> getContains() {
        return contains;
    }

    public void setContains(List<ContainsEntity> contains) {
        this.contains = contains;
    }

    public static class ContainsEntity implements Serializable{
        private String model;
        private String type;
        private String box;
        private String pailiang;
        private int seat;
        private int zidong;
        private int plan_id;
        private String image;
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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBox() {
            return box;
        }

        public void setBox(String box) {
            this.box = box;
        }

        public String getPailiang() {
            return pailiang;
        }

        public void setPailiang(String pailiang) {
            this.pailiang = pailiang;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public int getZidong() {
            return zidong;
        }

        public void setZidong(int zidong) {
            this.zidong = zidong;
        }

        public int getPlan_id() {
            return plan_id;
        }

        public void setPlan_id(int plan_id) {
            this.plan_id = plan_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public PlanEntity getPlan() {
            return plan;
        }

        public void setPlan(PlanEntity plan) {
            this.plan = plan;
        }

        public static class PlanEntity implements Serializable{
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getUnitMileage() {
                return unitMileage;
            }

            public void setUnitMileage(double unitMileage) {
                this.unitMileage = unitMileage;
            }

            public double getOutMileage() {
                return outMileage;
            }

            public void setOutMileage(double outMileage) {
                this.outMileage = outMileage;
            }

            public double getOutTime() {
                return outTime;
            }

            public void setOutTime(double outTime) {
                this.outTime = outTime;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getJijia() {
                return jijia;
            }

            public void setJijia(int jijia) {
                this.jijia = jijia;
            }

            public double getInsurance() {
                return insurance;
            }

            public void setInsurance(double insurance) {
                this.insurance = insurance;
            }

            public double getHasDriver() {
                return hasDriver;
            }

            public void setHasDriver(double hasDriver) {
                this.hasDriver = hasDriver;
            }

            public double getOthers() {
                return others;
            }

            public void setOthers(double others) {
                this.others = others;
            }

            public double getPoundage() {
                return poundage;
            }

            public void setPoundage(double poundage) {
                this.poundage = poundage;
            }

            public double getFranchiseFees() {
                return franchiseFees;
            }

            public void setFranchiseFees(double franchiseFees) {
                this.franchiseFees = franchiseFees;
            }
        }
    }
}
