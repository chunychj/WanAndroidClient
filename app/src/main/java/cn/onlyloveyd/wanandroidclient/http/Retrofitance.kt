package cn.onlyloveyd.wanandroidclient.http

import cn.onlyloveyd.wanandroidclient.BuildConfig
import cn.onlyloveyd.wanandroidclient.bean.ArticleResponse
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
class Retrofitance private constructor(){

    private val DEFAULT_TIMEOUT: Long = 15
    private val BASE_URL: String = "http://www.wanandroid.com/"

    var retrofit: Retrofit = initRetrofit()


    private object Holder {
        val INSTANCE = Retrofitance()
        val WANANDROIDAPI = INSTANCE.retrofit.create(WanAndroidAPI::class.java)
    }
    companion object{
        val instance:Retrofitance by lazy { Holder.INSTANCE }
        val wanAndroidAPI :WanAndroidAPI by lazy { Holder.WANANDROIDAPI }
    }
    private fun initRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()

        return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun createWanAndroidAPI():WanAndroidAPI{
        return retrofit.create(WanAndroidAPI::class.java)
    }

//    fun getArticles(subscriber: Observer<ArticleResponse>, pageNum:Int) {
////        wanAndroidAPI.getArticles(pageNum)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber)
//        baseOp(subscriber, wanAndroidAPI.getArticles(pageNum) )
//    }
//
//    private fun <T> baseOp(subscriber: Observer<T>, observable: Observable<T>) {
//        observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber)
//    }
}