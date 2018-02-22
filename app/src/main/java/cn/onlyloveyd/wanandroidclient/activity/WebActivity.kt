package cn.onlyloveyd.wanandroidclient.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.view.BGARefreshWebLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient


/**
 * 文 件 名: WebActivity
 * 创建日期: 2018/2/22 08:33
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class WebActivity : AppCompatActivity() {
    private var mAgentWeb: AgentWeb? = null
    private var mLinearLayout: LinearLayout? = null
    private var mToolbar: Toolbar? = null
    private var mTitleTextView: TextView? = null
    private var mAlertDialog: AlertDialog? = null

    private var url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web)


        mLinearLayout = this.findViewById(R.id.container) as LinearLayout
        mToolbar = this.findViewById(R.id.toolbar) as Toolbar
        mToolbar!!.setTitleTextColor(Color.WHITE)
        mToolbar!!.title = ""
        mTitleTextView = this.findViewById(R.id.toolbar_title) as TextView
        this.setSupportActionBar(mToolbar)
        if (supportActionBar != null) {
            // Enable the Up button
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        mToolbar!!.setNavigationOnClickListener { showDialog() }

        url = intent.getStringExtra("URL")

        val p = System.currentTimeMillis()

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator().defaultProgressBarColor()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .setWebLayout(BGARefreshWebLayout(this))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                //.interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(getUrl())

        val n = System.currentTimeMillis()
        Log.i("Info", "init used time:" + (n - p))


    }

    private val mWebViewClient = object : WebViewClient() {
    }
    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            if (mTitleTextView != null) {
                mTitleTextView!!.text = title
            }
        }
    }

    private fun getUrl(): String? {
        return url
    }


    private fun showDialog() {

        if (mAlertDialog == null) {
            mAlertDialog = AlertDialog.Builder(this, R.style.AppTheme_Dark_Dialog)
                    .setMessage("您确定要关闭该页面吗?")
                    .setNegativeButton("再看看") { _, _ ->
                        if (mAlertDialog != null) {
                            mAlertDialog!!.dismiss()
                        }
                    }//
                    .setPositiveButton("确定") { _, _ ->
                        if (mAlertDialog != null) {
                            mAlertDialog!!.dismiss()
                        }
                        this@WebActivity.finish()
                    }.create()
        }
        mAlertDialog!!.show()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        return if (mAgentWeb?.handleKeyEvent(keyCode, event)!!) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()

    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        Log.i("Info", "result:$requestCode result:$resultCode")
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onDestroy() {
        super.onDestroy()
        //mAgentWeb.destroy();
        mAgentWeb?.webLifeCycle?.onDestroy()
    }
}