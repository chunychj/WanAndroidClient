package cn.onlyloveyd.wanandroidclient.http

import cn.onlyloveyd.wanandroidclient.bean.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * 文 件 名: WanAndroidAPI
 * 创 建 人: 易冬
 * 创建日期: 2018/2/6 20:27
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
interface WanAndroidAPI {
    @GET("article/list/{pageNum}/json")
    fun getArticles(@Path("pageNum") pageNum:Int) : Observable<HttpResult<ArticleResponseBody>>

    @GET("tree/json")
    fun getKnowledgeTrees() : Observable<HttpResult<List<KnowledgeTreeBody>>>

    @GET("banner/json")
    fun getBanners() : Observable<HttpResult<List<Banner>>>

//    @GET("hotkey/json")
//    fun getHotKeys() : Observable<HotKeyResponse>

    @GET("friend/json")
    fun getFriendWebsites() : Observable<HttpResult<List<Friend>>>


//    @GET("article/list/{pageNum}/json?cid={cid}")
//    fun getKnowledgeTreeArticles(@Path("pageNum") pageNum: Int, @Path("cid") cid: Int) : Observable<ArticleResponse>


    @POST("user/login")
    @FormUrlEncoded
    fun loginWanAndroid(@Field("username") username : String,
                        @Field("password") password:String) : Observable<HttpResult<LoginData>>

    //收藏网址列表
    @GET("lg/collect/usertools/json")
    fun getCollections():Observable<ResponseBody>

    //收藏站内文章
    @POST("lg/collect/{cid}/json")
    fun collectInstationArticle(@Path("cid") cid: Int)

    //收藏站外文章
    @POST("lg/collect/add/json")
    fun collectOutArticle(@Field("title") title:String,
                          @Field("author") author:String,
                          @Field("link") link: String)
    //取消收藏文章
    @POST("lg/uncollect/{id}/json")
    fun cancelCollectionArticle(@Path("id") id:String)

    //收藏网址
    @POST("lg/collect/addtool/json")
    fun collectWebsite(@Field("name") name:String,
                       @Field("link") link: String)

    //编辑收藏网址
    @POST("lg/collect/updatetool/json")
    fun editCollectionWebsite(@Field("id") id:Int,
                              @Field("name") name: String,
                              @Field("link") link: String)
    //删除收藏网址
    @POST("lg/collect/deletetool/json")
    fun deleteCollectionWebsite(@Field("id") id: Int)

}