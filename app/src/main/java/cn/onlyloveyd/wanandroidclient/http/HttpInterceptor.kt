package cn.onlyloveyd.wanandroidclient.http

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 文 件 名: HttpInterceptor
 * 创建日期: 2018/2/11 08:25
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class HttpInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val request = builder.addHeader("Content-type", "application/json; charset=utf-8").build()
        return chain.proceed(request)
    }
}