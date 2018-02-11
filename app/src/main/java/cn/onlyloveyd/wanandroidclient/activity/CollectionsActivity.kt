package cn.onlyloveyd.wanandroidclient.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 文 件 名: CollectionsActivity
 * 创建日期: 2018/2/11 11:08
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class CollectionsActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_article)

        Retrofitance.wanAndroidAPI.getCollections().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe ( {
                    it->
                    System.err.println("yidong -- result = " + it.string())
                },{
                    it->
                    it.printStackTrace()
                } )
    }
}