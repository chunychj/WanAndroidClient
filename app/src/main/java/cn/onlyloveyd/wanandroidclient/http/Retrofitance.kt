package cn.onlyloveyd.wanandroidclient.http

import cn.onlyloveyd.wanandroidclient.BuildConfig
import cn.onlyloveyd.wanandroidclient.ext.Ext
import cn.onlyloveyd.wanandroidclient.ext.Preference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 文 件 名: Retrofitance
 * 创 建 人: 易冬
 * 创建日期: 2018/2/6 20:16
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
class Retrofitance private constructor() {

    var retrofit: Retrofit = initRetrofit()


    private object Holder {
        val INSTANCE = Retrofitance()
        val WANANDROIDAPI = INSTANCE.retrofit.create(WanAndroidAPI::class.java)
    }

    companion object {
        val instance: Retrofitance by lazy { Holder.INSTANCE }
        val wanAndroidAPI: WanAndroidAPI by lazy { Holder.WANANDROIDAPI }
    }

    private fun initRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        val httpInterceptor = HttpInterceptor()

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpInterceptor)
                .addInterceptor {
                    val request = it.request()
                    val response = it.proceed(request)
                    val requestUrl = request.url().toString()
                    val domain = request.url().host()
                    // set-cookie maybe has multi, login to save cookie
                    if ((requestUrl.contains(Ext.SAVE_USER_LOGIN_KEY) || requestUrl.contains(
                                    Ext.SAVE_USER_REGISTER_KEY
                            ))
                            && !response.headers(Ext.SET_COOKIE_KEY).isEmpty()) {
                        val cookies = response.headers(Ext.SET_COOKIE_KEY)
                        val cookie = Ext.encodeCookie(cookies)
                        saveCookie(requestUrl, domain, cookie)
                    }
                    response
                }
                .addInterceptor {
                    val request = it.request()
                    val builder = request.newBuilder()
                    val domain = request.url().host()
                    val url = request.url().toString()
                    if (domain.isNotEmpty() && (url.contains(Ext.COLLECTIONS_WEBSITE) || url.contains(Ext.UNCOLLECTIONS_WEBSITE))) {
                        val spDomain: String by Preference(domain, "")
                        val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
                        if (cookie.isNotEmpty()) {
                            builder.addHeader(Ext.COOKIE_NAME, cookie)
                        }
                    }
                    it.proceed(builder.build())
                }
                .retryOnConnectionFailure(true)
                .connectTimeout(Ext.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Ext.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(Ext.BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun saveCookie(url: String?, domain: String?, cookies: String) {
        System.err.println("yidong -- url = " + url + " domain = " + domain + " cookies = " + cookies)
        url ?: return
        var spUrl: String by Preference(url, cookies)
        @Suppress("UNUSED_VALUE")
        spUrl = cookies
        domain ?: return
        var spDomain: String by Preference(domain, cookies)
        @Suppress("UNUSED_VALUE")
        spDomain = cookies
    }
}