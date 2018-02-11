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

    val DEFAULT_TIMEOUT: Long = 15
    val BASE_URL: String = "http://www.wanandroid.com/"
    val SAVE_USER_LOGIN_KEY = "user/login"
    val SAVE_USER_REGISTER_KEY = "user/register"

    val COLLECTIONS_WEBSITE = "lg/collect"
    val UNCOLLECTIONS_WEBSITE = "lg/uncollect"

    val SET_COOKIE_KEY = "set-cookie"
    val COOKIE_NAME = "Cookie"

    val PREF_IS_LOGIN = "islogin"
    val PREF_USERNAME = "username"

    val LOGIN_REQUEST_CODE = 10001

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