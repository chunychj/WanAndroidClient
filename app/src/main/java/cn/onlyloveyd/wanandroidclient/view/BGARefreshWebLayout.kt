package cn.onlyloveyd.wanandroidclient.view

import android.app.Activity
import android.webkit.WebView
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.onlyloveyd.wanandroidclient.R
import com.just.agentweb.IWebLayout

/**
 * 文 件 名: BGARefreshWebLayout
 * 创建日期: 2018/2/22 08:58
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class BGARefreshWebLayout(activity: Activity) : IWebLayout<WebView ,BGARefreshLayout> {

    private val mBGARefreshLayout: BGARefreshLayout
    private val mWebView: WebView

    init {
        val mView = activity.layoutInflater.inflate(R.layout.web_layout, null)
        mBGARefreshLayout = mView.findViewById(R.id.bgarefreshlayout)
        mWebView = mView.findViewById(R.id.webView)
    }

    override fun getLayout(): BGARefreshLayout {
        return mBGARefreshLayout
    }

    override fun getWeb(): WebView? {
        return mWebView
    }
}