package cn.onlyloveyd.wanandroidclient.http

import cn.onlyloveyd.wanandroidclient.bean.*
import io.reactivex.Observable
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
    fun getArticles(@Path("pageNum") pageNum: Int): Observable<HttpResult<ArticleResponseBody>>

    @GET("tree/json")
    fun getKnowledgeTrees(): Observable<HttpResult<List<KnowledgeTreeBody>>>

    @GET("banner/json")
    fun getBanners(): Observable<HttpResult<List<Banner>>>

//    @GET("hotkey/json")
//    fun getHotKeys() : Observable<HotKeyResponse>

    @GET("friend/json")
    fun getFriendWebsites(): Observable<HttpResult<List<Friend>>>


    @GET("/article/list/{page}/json")
    fun getKnowledgeTreeArticles(@Path("page") page: Int, @Query("cid") cid: Int):Observable<HttpResult<ArticleResponseBody>>


    @POST("user/login")
    @FormUrlEncoded
    fun loginWanAndroid(@Field("username") username: String,
                        @Field("password") password: String): Observable<HttpResult<LoginData>>

    @POST("user/register")
    @FormUrlEncoded
    fun registerWanAndroid(@Field("username") username: String,
                           @Field("password") password: String,
                           @Field("repassword") repassword: String): Observable<HttpResult<LoginData>>


    //收藏文章列表
    @GET("lg/collect/list/{pageNum}/json")
    fun getCollectionArticles(@Path("pageNum") pageNum: Int): Observable<HttpResult<CollectionResponseBody<CollectionArticle>>>

    //收藏站内文章
    @POST("lg/collect/{cid}/json")
    fun collectInstationArticle(@Path("cid") cid: Int): Observable<HttpResult<Any>>

    //收藏站外文章
    @POST("lg/collect/add/json")
    fun collectOutArticle(@Field("title") title: String,
                          @Field("author") author: String,
                          @Field("link") link: String)

    //文章列表中取消收藏文章
    @POST("lg/uncollect_originId/{id}/json")
    fun cancelCollectionArticle(@Path("id") id: Int): Observable<HttpResult<Any>>

    //收藏列表中取消收藏文章
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    fun removeCollectionArticle(@Path("id") id: Int,
                                @Field("originId") originId: Int = -1): Observable<HttpResult<Any>>

    //收藏网址列表
    @GET("lg/collect/usertools/json")
    fun getCollectionWebsites(): Observable<HttpResult<MutableList<CollectionWebsite>>>

    //收藏网址
    @FormUrlEncoded
    @POST("lg/collect/addtool/json")
    fun collectWebsite(@Field("name") name: String,
                       @Field("link") link: String)

    //编辑收藏网址
    @FormUrlEncoded
    @POST("lg/collect/updatetool/json")
    fun editCollectionWebsite(@Field("id") id: Int,
                              @Field("name") name: String,
                              @Field("link") link: String): Observable<HttpResult<CollectionWebsite>>

    //删除收藏网址
    @FormUrlEncoded
    @POST("lg/collect/deletetool/json")
    fun deleteCollectionWebsite(@Field("id") id: Int): Observable<HttpResult<Any>>

}