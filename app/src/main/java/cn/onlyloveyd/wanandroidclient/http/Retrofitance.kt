package cn.onlyloveyd.wanandroidclient.http

import okhttp3.OkHttpClient
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
class Retrofitance() {
    private val retrofit: Retrofit
    private val okHttpClient : OkHttpClient
    private val wanAndroidAPI : WanAndroidAPI

    init {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClient = httpClientBuilder.build()

        retrofit = Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        wanAndroidAPI = retrofit.create(WanAndroidAPI::class.java)
    }

    //在访问HttpMethods时创建单例
    private object SingletonHolder {
        val INSTANCE = Retrofitance()
    }
    companion object {
        val BASE_URL = "http://www.wanandroid.com/"
        private val DEFAULT_TIMEOUT = 5
        //获取单例
        val instance: Retrofitance
            get() = SingletonHolder.INSTANCE
    }

}