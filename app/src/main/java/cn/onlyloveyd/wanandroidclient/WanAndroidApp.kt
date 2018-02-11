package cn.onlyloveyd.wanandroidclient

import android.app.Application
import cn.onlyloveyd.wanandroidclient.ext.Preference
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * 文 件 名: WanAndroidApp
 * 创建日期: 2018/2/7 10:00
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class WanAndroidApp : Application() {
    private val TAG = "WanAndroid"

    override fun onCreate() {
        super.onCreate()
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .tag(TAG)
                .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
        Preference.setContext(applicationContext)
    }
}