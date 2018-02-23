package cn.onlyloveyd.wanandroidclient.bean

import com.squareup.moshi.Json


/**
 * 文 件 名: DataRepository
 * 创 建 人: 易冬
 * 创建日期: 2018/2/6 20:27
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
data class HttpResult<T>(@Json(name = "data") val data: T,
                         @Json(name = "errorCode") val errorCode: Int,
                         @Json(name = "errorMsg") val errorMsg: String)

//文章
data class ArticleResponseBody(
        @Json(name = "curPage") val curPage: Int,
        @Json(name = "datas") val datas: MutableList<Article>,
        @Json(name = "offset") val offset: Int,
        @Json(name = "over") val over: Boolean,
        @Json(name = "pageCount") val pageCount: Int,
        @Json(name = "size") val size: Int,
        @Json(name = "total") val total: Int
)

//文章
data class Article(
        @Json(name = "apkLink") val apkLink: String,
        @Json(name = "author") val author: String,
        @Json(name = "chapterId") val chapterId: Int,
        @Json(name = "chapterName") val chapterName: String,
        @Json(name = "collect") var collect: Boolean,
        @Json(name = "courseId") val courseId: Int,
        @Json(name = "desc") val desc: String,
        @Json(name = "envelopePic") val envelopePic: String,
        @Json(name = "id") val id: Int,
        @Json(name = "link") val link: String,
        @Json(name = "niceDate") val niceDate: String,
        @Json(name = "origin") val origin: String,
        @Json(name = "projectLink") val projectLink: String,
        @Json(name = "publishTime") val publishTime: Long,
        @Json(name = "title") val title: String,
        @Json(name = "visible") val visible: Int,
        @Json(name = "zan") val zan: Int
)

//横幅
data class Banner(
        @Json(name = "desc") val desc: String,
        @Json(name = "id") val id: Int,
        @Json(name = "imagePath") val imagePath: String,
        @Json(name = "isVisible") val isVisible: Int,
        @Json(name = "order") val order: Int,
        @Json(name = "title") val title: String,
        @Json(name = "type") val type: Int,
        @Json(name = "url") val url: String
)

data class HotKey(
        @Json(name = "id") val id: Int,
        @Json(name = "link") val link: String,
        @Json(name = "name") val name: String,
        @Json(name = "order") val order: Int,
        @Json(name = "visible") val visible: Int
)

//常用网站
data class Friend(
        @Json(name = "icon") val icon: String,
        @Json(name = "id") val id: Int,
        @Json(name = "link") val link: String,
        @Json(name = "name") val name: String,
        @Json(name = "order") val order: Int,
        @Json(name = "visible") val visible: Int
)

//知识体系
data class KnowledgeTreeBody(
        @Json(name = "children") val children: MutableList<Knowledge>,
        @Json(name = "courseId") val courseId: Int,
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "order") val order: Int,
        @Json(name = "parentChapterId") val parentChapterId: Int,
        @Json(name = "visible") val visible: Int
)

data class Knowledge(
        @Json(name = "children") val children: List<Any>,
        @Json(name = "courseId") val courseId: Int,
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "order") val order: Int,
        @Json(name = "parentChapterId") val parentChapterId: Int,
        @Json(name = "visible") val visible: Int
)

data class LoginData(
        @Json(name = "collectIds") val collectIds: List<Any>,
        @Json(name = "email") val email: String,
        @Json(name = "icon") val icon: String,
        @Json(name = "id") val id: Int,
        @Json(name = "password") val password: String,
        @Json(name = "type") val type: Int,
        @Json(name = "username") val username: String
)

//收藏网站
data class CollectionWebsite(
        @Json(name = "desc") val desc: String,
        @Json(name = "icon") val icon: String,
        @Json(name = "id") val id: Int,
        @Json(name = "link") var link: String,
        @Json(name = "name") var name: String,
        @Json(name = "order") val order: Int,
        @Json(name = "userId") val userId: Int,
        @Json(name = "visible") val visible: Int
)


data class CollectionResponseBody<T>(
        @Json(name = "curPage") val curPage: Int,
        @Json(name = "datas") val datas: List<T>,
        @Json(name = "offset") val offset: Int,
        @Json(name = "over") val over: Boolean,
        @Json(name = "pageCount") val pageCount: Int,
        @Json(name = "size") val size: Int,
        @Json(name = "total") val total: Int
)

data class CollectionArticle(
        @Json(name = "author") val author: String,
        @Json(name = "chapterId") val chapterId: Int,
        @Json(name = "chapterName") val chapterName: String,
        @Json(name = "courseId") val courseId: Int,
        @Json(name = "desc") val desc: String,
        @Json(name = "envelopePic") val envelopePic: String,
        @Json(name = "id") val id: Int,
        @Json(name = "link") val link: String,
        @Json(name = "niceDate") val niceDate: String,
        @Json(name = "origin") val origin: String,
        @Json(name = "originId") val originId: Int,
        @Json(name = "publishTime") val publishTime: Long,
        @Json(name = "title") val title: String,
        @Json(name = "userId") val userId: Int,
        @Json(name = "visible") val visible: Int,
        @Json(name = "zan") val zan: Int
)