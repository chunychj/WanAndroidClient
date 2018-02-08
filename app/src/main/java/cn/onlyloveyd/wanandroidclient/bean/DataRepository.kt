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

data class ArticleResponse(
		@Json(name = "data") val data: ArticleResponseBody,
		@Json(name = "errorCode") val errorCode: Int,
		@Json(name = "errorMsg") val errorMsg: String
)

data class ArticleResponseBody(
		@Json(name = "curPage") val curPage: Int,
		@Json(name = "datas") val datas: MutableList<Article>,
		@Json(name = "offset") val offset: Int,
		@Json(name = "over") val over: Boolean,
		@Json(name = "pageCount") val pageCount: Int,
		@Json(name = "size") val size: Int,
		@Json(name = "total") val total: Int
)

data class Article(
		@Json(name = "apkLink") val apkLink: String,
		@Json(name = "author") val author: String,
		@Json(name = "chapterId") val chapterId: Int,
		@Json(name = "chapterName") val chapterName: String,
		@Json(name = "collect") val collect: Boolean,
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


data class BannerResponse(
		@Json(name = "data") val data: List<Banner>,
		@Json(name = "errorCode") val errorCode: Int,
		@Json(name = "errorMsg") val errorMsg: String
)

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


data class HotKeyResponse(
		@Json(name = "data") val data: List<HotKey>,
		@Json(name = "errorCode") val errorCode: Int,
		@Json(name = "errorMsg") val errorMsg: String
)

data class HotKey(
		@Json(name = "id") val id: Int,
		@Json(name = "link") val link: String,
		@Json(name = "name") val name: String,
		@Json(name = "order") val order: Int,
		@Json(name = "visible") val visible: Int
)


data class FriendResponse(
		@Json(name = "data") val data: List<Friend>,
		@Json(name = "errorCode") val errorCode: Int,
		@Json(name = "errorMsg") val errorMsg: String
)

data class Friend(
		@Json(name = "icon") val icon: String,
		@Json(name = "id") val id: Int,
		@Json(name = "link") val link: String,
		@Json(name = "name") val name: String,
		@Json(name = "order") val order: Int,
		@Json(name = "visible") val visible: Int
)

data class KnowledgeTreeResponse(
		@Json(name = "data") val data: MutableList<KnowledgeTreeBody>,
		@Json(name = "errorCode") val errorCode: Int,
		@Json(name = "errorMsg") val errorMsg: String
)

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