package com.example.temple.network;

public class Comments {

    //    //生产
    public static String BASE_URL = "https://xyxj-api.xuanyuanxianjing.com";


//        public static String BASE_URL ="https://7344013zl4.zicp.fun";//本地域名
//    public static String BASE_URL = "http://192.168.0.111:8033";//测试  192.168.0.102:8032

    public static String BASE_IMAGE_URL = "https://41vy728617.qicp.vip/";//图片URL


    public static String USER_LOGIN = BASE_URL + "/api/v1/user/login-user";
    public static String REGISTER_USER = BASE_URL + "/api/v1/user/register-user";
    public static String SMS_CODE = BASE_URL + "/api/v1/user/smscode";
    public static String BANNER = BASE_URL + "/api/v1/banner/list";
    public static String SEARCH_KEY = BASE_URL + "/api/v1/goods/query-good-name-page";

    //获取用户信息
    public static String GET_USER_INFO = BASE_URL + "/api/v1/user/get-user-info";
    //获取排行榜  好物推荐
    public static String HOME_DATA = BASE_URL + "/api/v1/goods/get-index-data";
    //获取商品详情
    public static String GET_DETAIL = BASE_URL + "/api/v1/goods/query-good-load";
    //获取地址列表
    public static String ADDRESS_LIST = BASE_URL + "/api/v1/address/query-page";
    public static String ADD_ADDRESS = BASE_URL + "/api/v1/address/create";
    public static String EDIT_ADDRESS = BASE_URL + "/api/v1/address/update";
    public static String DEL_ADDRESS = BASE_URL + "/api/v1/address/delete";

    public static String CHECK_UPDATE = BASE_URL + "/api/v1/apk/check";

    //获取系统配置数据
    public static String GET_INFO = BASE_URL + "/api/v1/dict/get-abount";
    public static String GET_TYPE = BASE_URL + "/api/v1/good-type/query-all";
    public static String GET_PRO_BY_TYPE = BASE_URL + "/api/v1/goods/query-good-enum-page-type";
    public static String QUERY_GOOD_TYPE_PAGE = BASE_URL + "/api/v1/goods/query-good-type-page";


    //订单
    public static String ORDER_DETAIL = BASE_URL + "/api/v1/order/get-my-order-load";
    public static String CANCEL_ORDER = BASE_URL + "/api/v1/order/cancel-my-order";
    public static String TAKE_ORDER = BASE_URL + "/api/v1/order/create";
    public static String CONFIRM_LOGISSTICS = BASE_URL + "/api/v1/order/confirm-my-order";
    public static String CAR_ORDER = BASE_URL + "/api/v1/order/create-cart-order";
    public static String CHECK_ORDER = BASE_URL + "/api/v1/gyl/order/before-check-again";
    public static String GET_LOGISSTICS = BASE_URL + "/api/v1/order/get-express-no/";
    public static String GET_RECOMMED = BASE_URL + "/api/v1/goods/query-push-good-page";
    public static String ORDER_LIST = BASE_URL + "/api/v1/order/get-my-order";

    public static String FILE_UPLOAD = BASE_URL + "/api/v1/file/upload";
    public static String UPDATE_INFO = BASE_URL + "/api/v1/user/set-user-avatar-url";
    public static String GET_EARNINGS_APP = BASE_URL + "/api/v1/earning/get-earnings-app";
    //每日统计
    public static String GET_MY_CAUSE_CALC = BASE_URL + "/api/v1/cause/get-my-cause-calc";
    //获取我的贡献详情
    public static String GET_MY_CAUSE_DETAIL = BASE_URL + "/api/v1/cause/get-my-cause-detail";

    //提交贡献值转出申请
    public static String COMMIT_WITHDRAW = BASE_URL + "/api/v1/withdraw/commit-withdraw";
    //转出记录明细
    public static String GET_MY_WITHDRAW = BASE_URL + "/api/v1/withdraw/get-my-withdraw";
    //获取我的信众
    public static String GET_MY_DIRECT_USER = BASE_URL + "/api/v1/user/get-my-direct-user";
    public static String UPDATE_PWD = BASE_URL + "/api/v1/user/update-password";
    public static String LOGIN_OUT = BASE_URL + "/api/v1/user/login-out";
    public static String FIND_PWD = BASE_URL + "/api/v1/user/reset-password";


    //康宁模块 借口
    //康宁类型查看详情
    public static String KANGNING_DETAIL = BASE_URL + "/api/v1/healthy-system/get-healthy-load";
    public static String KANGNING_ORDER = BASE_URL + "/api/v1/healthy-system/submit-healthy";
    public static String KANGNING_QIYUANQIANG = BASE_URL + "/api/v1/healthy-system/get-healthy-over-page";


    //好德
    public static String HAODE_COPY = BASE_URL + "/api/v1/haoder-system/get-haoder-copy-page";
    public static String HAODE_CHECK = BASE_URL + "/api/v1/haoder-system/check-haoder-copy";
    public static String HAODE_SAVE = BASE_URL + "/api/v1/haoder-system/save";
    public static String HAODE_SONG = BASE_URL + "/api/v1/haoder-system/get-haoder-recite-page";
    public static String HAODE_RANK = BASE_URL + "/api/v1/haoder-system/query-haoder-ranking";

    //预约老师
    public static String TEACHER_LIST = BASE_URL + "/api/v1/teacher/find-teacher-list";
    public static String ABOUT_TEACHER = BASE_URL + "/api/v1/teacher/about-teacher";
    public static String MY_TEACHER_LIST = BASE_URL + "/api/v1/teacher/get-my-teacher-list";
    public static String CANCLE_TEACHER_LIST = BASE_URL + "/api/v1/teacher/cancel-teacher-about";
    public static String OVER_TEACHER_LIST = BASE_URL + "/api/v1/teacher/over-teacher-order";
    public static String REVIEW_TEACHER_LIST = BASE_URL + "/api/v1/teacher/review-teacher-order";
    public static String DETAILS_TEACHER_LIST = BASE_URL + "/api/v1/teacher/get-my-teacher-load";

    //长寿
    public static String ZHANGSHOU_INFO = BASE_URL + "/api/v1/long-evity/query-long-evity-list";
    public static String TIZHI_INFO = BASE_URL + "/api/v1/long-evity/submit-test";

    //道历
    public static String DAOLI_INFO = BASE_URL + "/api/v1/teacher/get-calendar";

    public static final int HOME_TO_LOGIN = 1000;
    public static final int HOME_TO_AUTHER = 1100;
    public static final int DETAIL_TO_BUY = 1200;
    public static final int UPGRADE_TO_CONFIRM = 1300;

    public static final int UNABLE_TO_LOGIN = 1000;

    public static final int HOME_TO_ACCOUNT = 2000;

    public static final int TO_SETTING = 3000;
    public static final int ADD_CARD = 4000;
    public static final int TO_ADDRESS = 5000;
    public static final int TO_EXCHANGE = 6000;

    public static final int SDK_PAY_FLAG = 1;


    public static final String ON_CAR = "CarRefresh";
    public static final String ON_ORDER = "OrderRefresh";
    public static final String ON_ORDER_CONFIRM = "OrderConfirm";

    public static final String PAY_SUCCESS = "pay_success";//康宁 供奉支付成功
    public static final String PAY_TEACHER_SUCCESS = "pay_teacher_success";//预约老师支付成功
    public static final String TEACHER_CANCLE_SUCCESS = "teacher_cancle_success";//取消预约
    public static final String TEACHER_OVER_SUCCESS = "teacher_over_success";//完成预约
    public static final String HAODE_SAVE_SUCCESS = "haode_save_success";
}
