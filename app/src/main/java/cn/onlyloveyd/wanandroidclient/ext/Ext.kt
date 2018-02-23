package cn.onlyloveyd.wanandroidclient.ext

import cn.onlyloveyd.wanandroidclient.R

/**
 * 文 件 名: Ext
 * 创 建 人: 易冬
 * 创建日期: 2018/2/6 21:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
object Ext {
    const val HTTP_ERROR = -1

    const val DEFAULT_TIMEOUT: Long = 15
    const val BASE_URL: String = "http://www.wanandroid.com/"
    const val SAVE_USER_LOGIN_KEY = "user/login"
    const val SAVE_USER_REGISTER_KEY = "user/register"

    const val COLLECTIONS_WEBSITE = "lg/collect"
    const val UNCOLLECTIONS_WEBSITE = "lg/uncollect"
    const val ARTICLE_WEBSITE = "article"

    const val SET_COOKIE_KEY = "set-cookie"
    const val COOKIE_NAME = "Cookie"

    const val PREF_IS_LOGIN = "islogin"
    const val PREF_USERNAME = "username"

    const val LOGIN_REQUEST_CODE = 10001

    val COLLECTIONS_SEGMENT_TITLES = arrayOf("文章", "网址")

    val BackgroundRepository = mutableListOf(
            R.drawable.bg_tag_one,
            R.drawable.bg_tag_two,
            R.drawable.bg_tag_three,
            R.drawable.bg_tag_four,
            R.drawable.bg_tag_five,
            R.drawable.bg_tag_six,
            R.drawable.bg_tag_seven,
            R.drawable.bg_tag_eight
    )

    val HollowBackgroundRepository = mutableListOf(
            R.drawable.hollow_tag_one,
            R.drawable.hollow_tag_two,
            R.drawable.hollow_tag_three,
            R.drawable.hollow_tag_four,
            R.drawable.hollow_tag_five,
            R.drawable.hollow_tag_six,
            R.drawable.hollow_tag_seven,
            R.drawable.hollow_tag_eight
    )

    fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies
                .map { cookie ->
                    cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                }
                .forEach {
                    it.filterNot { set.contains(it) }.forEach { set.add(it) }
                }

        val ite = set.iterator()
        while (ite.hasNext()) {
            val cookie = ite.next()
            sb.append(cookie).append(";")
        }

        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }

        return sb.toString()
    }
}