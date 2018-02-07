package cn.onlyloveyd.wanandroidclient.http

import cn.onlyloveyd.wanandroidclient.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
    fun getBanners() : Observable<HttpResult<Banner>>

    @GET("hotkey/json")
    fun getHotKeys() : Observable<HotKeyResponse>

    @GET("friend/json")
    fun getFriendWebsites() : Observable<FriendResponse>


    @GET("article/list/{pageNum}/json?cid={cid}")
    fun getKnowledgeTreeArticles(@Path("pageNum") pageNum: Int, @Path("cid") cid: Int) : Observable<ArticleResponse>

//    @POST("/article/query/{pageNum}/json")

}