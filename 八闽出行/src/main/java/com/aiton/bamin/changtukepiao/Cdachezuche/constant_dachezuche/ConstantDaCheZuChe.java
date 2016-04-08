package com.aiton.bamin.changtukepiao.Cdachezuche.constant_dachezuche;

import com.aiton.bamin.changtukepiao.Zeverything.constant.EverythingConstant;

/**
 * Created by Administrator on 2016/3/14.
 */
public class ConstantDaCheZuChe
{
    public class URL
    {
        //取车城市列表接口地址 传入的参数:page 默认值0
        public static final String CITY_LIST = EverythingConstant.HOST + "/bmpw/zc/store/loadcities";

        //机构认证服务接口 String code,String password，code为机构编号  password为密码，返回true和false
        public static final String DACHEZUCHE_COMFIRE_UNIT_INFO = EverythingConstant.HOST + "/bmpw/zc/institutions/checkinstitutions";

        //司机列表接口地址 传入的参数:page 默认值0
        public static final String DRIVER_LIST = EverythingConstant.HOST + "/bmpw/zc/driver/loadfreedriver";

        //获取车辆信息传入参数Integer lei
        public static final String GET_CAR_INFO = EverythingConstant.HOST + "/bmpw/zc/order/loadcarbylei";

        //根据车辆不同类型加载该类型下所有车辆的信息 传入参数 lei=0 page=0  0:公务车 1：商务车 2：执法车 3：越野车 4：皮卡 5：客车
        public static final String GET_CAR_LIST = EverythingConstant.HOST + "/bmpw/zc/car/loadcarsbylei";

        //获取门店地址Marker显示于地图上  传入String city，城市名
        public static final String GET_STORES_MARKERS_LATLNG = EverythingConstant.HOST + "/bmpw/zc/store/loadstorebycity";

        //机构租车提交订单
        /**
         * 参数：Integer plan_id;//租赁计划id     Timestamp zuchuDate; //租出时间     Timestamp planReturnDate;//计划还车时间
         * Double price;//总价     Integer status;//0：企业租车 1；个人租车     Double insurance;//保险金额     Integer getCar;//取车地点
         * Integer returnCar;//还车地点     Integer hasDriver;//0:带 1：不带     Integer driverId;//司机的id     Integer carId;//汽车的id
         * Integer lei;//套餐的类型     String institutionsCode;//企业账号     Integer accountId;//用户id
         */
        public static final String COMMIT_ORDER = EverythingConstant.HOST + "/bmpw/zc/order/institutions/addorder";

        //企业用车查询订单列表 Integer account_id,Integer page
        public static final String GET_ORDER_LIST_INSTITUTIONS = EverythingConstant.HOST + "/bmpw/zc/order/institutions/loadbyaccount";

        //个人租车查询订单列表 Integer account_id,Integer page
        public static final String GET_ORDER_LIST_PERSON = EverythingConstant.HOST + "/bmpw/zc/order/person/loadbyaccount";

        //个人租车取消订单列表  传入order_id
        public static final String CANCEL_ORDER = EverythingConstant.HOST + "/bmpw/zc/order/cancelorder";

        //查询订单详情
        public static final String QUERY_ORDER_DETAIL = EverythingConstant.HOST + "/bmpw/zc/order/details";
    }

    /**
     * 请求码
     */
    public class RequestCode
    {
        //自驾租车选择取车城市
        public static final int ZIJIAZUCHE_TAKE_CAR_CITY = 0;
        //机构租车选城市
        public static final int JIGOUZUCHE_TAKE_CAR_CITY = 1;
        //选司机
        public static final int JIGOUZUCHE_CHOOSE_DRIVER = 2;
        //取车门店地图
        public static final int JIGOUZUCHE_TAKE_CAR_MAP = 3;
        //还车门店地图
        public static final int JIGOUZUCHE_RETURN_CAR_MAP = 4;
        //自家租车选择还车城市
        public static final int ZIJIAZUCHE_RETURN_CAR_CITY = 5;
    }

    /**
     * 返回码
     */
    public class ResultCode
    {
        //选城市
        public static final int CHOOSE_CITY = 0;
        //选司机
        public static final int JIGOUZUCHE_CHOOSE_DRIVER = 1;
        //选门店
        public static final int CHOOSE_STORE = 2;
    }

    public class IntentKey
    {
        //跳转到门店地图_取车城市的KEY
        public static final String CITY = "city_name";
        //为区分还车门店还是取车门店
        public static final String GET_MAP_LOC_KEY = "get_map_loc";
        //跳转到门店地图_取车的KEY
        public static final int GET_MAP_LOC_GET = 1;
        //跳转到门店地图_还车的KEY
        public static final int GET_MAP_LOC_RETURN = 2;

        //选择城市地区的KEY
        public static final String CHOOSE_CITY = "choose_city";
        //选择城市地区的pisotion
        public static final String CHOOSE_CITY_POSITION = "choose_city_position";

        //取车门店返回值的KEY
        public static final String STORES_MAP_KEY = "stores_map_marker_title";

        //选择司机的返回值的KEY
        public static final String DRIVER_NAME = "driverName";
        public static final String DRIVER_ID = "driverID";

        //第一次选择时要传递的对象值的KEY
        public static final String CHOOSE_FRIST_INFO = "choose_frist_info";

        //门店ID的KEY
        public static final String STORES_ID_KEY = "storesId";

        //机构用车提交订单后返回到订单列表的KEY
        public static final String BACK_TO_ORDER_LIST_KEY = "ji_guo_zu_che_back_key";
        //机构用车提交订单后返回到订单列表的Intent值
        public static final int JI_GUO_ZU_CHE_BACK_INT = 11;
        //自驾用车提交订单后返回到订单列表的Intent值
        public static final int ZI_JIA_ZU_CHE_BACK_INT = 12;
    }
}
