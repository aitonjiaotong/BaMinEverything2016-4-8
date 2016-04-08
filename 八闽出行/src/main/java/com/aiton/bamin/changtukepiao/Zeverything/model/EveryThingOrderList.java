package com.aiton.bamin.changtukepiao.Zeverything.model;

import java.util.List;

/**
 * Created by Administrator on 2016/4/6.
 */
public class EveryThingOrderList
{


    /**
     * contains : [{"id":479,"order_id":"379","date":1460006693000,"account_id":5,"type":1,"price":18250,"yuliu":null,"flag":1},{"id":451,"order_id":"2016-04-07-12a4773b-39fc-4661-8a3f-62bdbc32f522","date":1460001903000,"account_id":5,"type":0,"price":0.01,"yuliu":"永安-村头","flag":0},{"id":478,"order_id":"378","date":1460001130000,"account_id":5,"type":1,"price":560,"yuliu":null,"flag":1},{"id":477,"order_id":"377","date":1460001089000,"account_id":5,"type":1,"price":18250,"yuliu":null,"flag":1},{"id":450,"order_id":"2016-04-07-0a47344f-3631-4002-9521-f8ec87d56b81","date":1460000395000,"account_id":5,"type":0,"price":0.01,"yuliu":"永安-村头","flag":0},{"id":449,"order_id":"2016-04-07-978a1640-71ad-427e-b966-e1b96277d3cb","date":1459999602000,"account_id":5,"type":0,"price":0.01,"yuliu":"永安-村头","flag":0},{"id":472,"order_id":"372","date":1459995296000,"account_id":5,"type":1,"price":560,"yuliu":null,"flag":1},{"id":471,"order_id":"371","date":1459995259000,"account_id":5,"type":1,"price":18250,"yuliu":null,"flag":1}]
     * num : 10
     */

    private CodeEntity code;
    /**
     * code : {"contains":[{"id":479,"order_id":"379","date":1460006693000,"account_id":5,"type":1,"price":18250,"yuliu":null,"flag":1},{"id":451,"order_id":"2016-04-07-12a4773b-39fc-4661-8a3f-62bdbc32f522","date":1460001903000,"account_id":5,"type":0,"price":0.01,"yuliu":"永安-村头","flag":0},{"id":478,"order_id":"378","date":1460001130000,"account_id":5,"type":1,"price":560,"yuliu":null,"flag":1},{"id":477,"order_id":"377","date":1460001089000,"account_id":5,"type":1,"price":18250,"yuliu":null,"flag":1},{"id":450,"order_id":"2016-04-07-0a47344f-3631-4002-9521-f8ec87d56b81","date":1460000395000,"account_id":5,"type":0,"price":0.01,"yuliu":"永安-村头","flag":0},{"id":449,"order_id":"2016-04-07-978a1640-71ad-427e-b966-e1b96277d3cb","date":1459999602000,"account_id":5,"type":0,"price":0.01,"yuliu":"永安-村头","flag":0},{"id":472,"order_id":"372","date":1459995296000,"account_id":5,"type":1,"price":560,"yuliu":null,"flag":1},{"id":471,"order_id":"371","date":1459995259000,"account_id":5,"type":1,"price":18250,"yuliu":null,"flag":1}],"num":10}
     * message : 查询成功
     */

    private String message;

    public CodeEntity getCode()
    {
        return code;
    }

    public void setCode(CodeEntity code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public static class CodeEntity
    {
        private int num;
        /**
         * id : 479
         * order_id : 379
         * date : 1460006693000
         * account_id : 5
         * type : 1
         * price : 18250.0
         * yuliu : null
         * flag : 1
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
            private int id;
            private String order_id;
            private long date;
            private int account_id;
            private int type;
            private double price;
            private String yuliu;
            private int flag;

            public int getId()
            {
                return id;
            }

            public void setId(int id)
            {
                this.id = id;
            }

            public String getOrder_id()
            {
                return order_id;
            }

            public void setOrder_id(String order_id)
            {
                this.order_id = order_id;
            }

            public long getDate()
            {
                return date;
            }

            public void setDate(long date)
            {
                this.date = date;
            }

            public int getAccount_id()
            {
                return account_id;
            }

            public void setAccount_id(int account_id)
            {
                this.account_id = account_id;
            }

            public int getType()
            {
                return type;
            }

            public void setType(int type)
            {
                this.type = type;
            }

            public double getPrice()
            {
                return price;
            }

            public void setPrice(double price)
            {
                this.price = price;
            }

            public String getYuliu()
            {
                return yuliu;
            }

            public void setYuliu(String yuliu)
            {
                this.yuliu = yuliu;
            }

            public int getFlag()
            {
                return flag;
            }

            public void setFlag(int flag)
            {
                this.flag = flag;
            }
        }
    }
}
