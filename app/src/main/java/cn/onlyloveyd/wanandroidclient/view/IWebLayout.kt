package cn.onlyloveyd.wanandroidclient.view

import android.view.ViewGroup
import android.webkit.WebView

/**
 * 文 件 名: IWebLayout
 * 创建日期: 2018/2/22 08:59
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
interface IWebLayout<out T:WebView, out V:ViewGroup> {
    fun getLayout():V
    fun getWeb():T
}