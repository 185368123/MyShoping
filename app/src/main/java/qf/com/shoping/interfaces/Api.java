package qf.com.shoping.interfaces;

import java.util.Map;

import qf.com.shoping.bean.CatalogueBean;
import qf.com.shoping.bean.DetailBean_a;
import qf.com.shoping.bean.DetailBean_b;
import qf.com.shoping.bean.DetailBean_c;
import qf.com.shoping.bean.DiscoverBean;
import qf.com.shoping.bean.HomeHotTopicBean;
import qf.com.shoping.bean.HomeRollPageBean;
import qf.com.shoping.bean.HomeRollPageDetailBean;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by li on 2017/2/20.
 */

public interface Api {
    //首页广告轮播
    @FormUrlEncoded
    @POST("recommend/operationElement")
    Call<HomeRollPageBean> getRollPageBean(@FieldMap Map<String,String> params);
    //首页广告轮播详情列表界面
    @FormUrlEncoded
    @POST("topics/topic/listByIds")
    Call<HomeRollPageDetailBean> getRollPageDetailBean(@FieldMap Map<String,String> params);
    //详情界面
    @FormUrlEncoded
    @POST("topic/newInfo")
    Call<DetailBean_a> getDetailBean_a(@FieldMap Map<String,String> params);
    @FormUrlEncoded
    @POST("topic/newInfo")
    Call<DetailBean_b> getDetailBean_b(@FieldMap Map<String,String> params);
    @POST("topic/newInfo")
    Call<String> getDetailBean_c(@FieldMap Map<String,String> params);

    //首页推荐
    @FormUrlEncoded
    @POST("recommend/index")
    Call<HomeHotTopicBean>  getHomeRecommendTopic(@FieldMap Map<String,String> params);
    //首页最新
    @FormUrlEncoded
    @POST("recommend/newestTopic")
    Call<HomeHotTopicBean>  getHomeNewestTopic(@FieldMap Map<String,String> params);
    //首页热门
    @FormUrlEncoded
    @POST("recommend/hotTopicList")
    Call<HomeHotTopicBean>  getHomeHotTopic(@FieldMap Map<String,String> params);
    //首页礼物.美食 生活 设计感 家居 数码 阅读 学生党 上班族 美妆 护理 户外运动通用
    @FormUrlEncoded
    @POST("topics/topic/listByAttribute")
    Call<HomeHotTopicBean>  getHomeGiftTopic(@FieldMap Map<String,String> params);

    //发现列表地址
    @FormUrlEncoded
    @POST("post/index/index")
    Call<DiscoverBean>  getDiscoverBean(@FieldMap Map<String,String> params);

    //发现栏最新
    @FormUrlEncoded
    @POST("post/index/listByNew")
    Call<DiscoverBean>  getDiscoverListByNew(@FieldMap Map<String,String> params);

    //发现栏热门
    @FormUrlEncoded
    @POST("post/index/listByRec")
    Call<DiscoverBean>  getDiscoverListByRec(@FieldMap Map<String,String> params);

    //主页搜索
    @FormUrlEncoded
    @POST("category/list")
    Call<CatalogueBean>  getList(@FieldMap Map<String,String> params);



}

