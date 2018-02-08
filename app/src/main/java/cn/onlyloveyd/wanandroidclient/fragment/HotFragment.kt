package cn.onlyloveyd.wanandroidclient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.onlyloveyd.wanandroidclient.R
import kotlinx.android.synthetic.main.fragment_hot.*
import me.yokeyword.fragmentation.SupportFragment
import java.util.Arrays.asList
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import cn.bingoogolapple.bgabanner.BGABanner
import cn.onlyloveyd.wanandroidclient.bean.Article
import cn.onlyloveyd.wanandroidclient.bean.Banner
import cn.onlyloveyd.wanandroidclient.bean.HttpResult
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import com.orhanobut.logger.Logger
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import cn.bingoogolapple.bgabanner.BGALocalImageSize




/**
 * 文 件 名: HotFragment
 * 创建日期: 2018/2/8 09:57
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class HotFragment:SupportFragment() {
    private val datas = mutableListOf<Banner>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Retrofitance.wanAndroidAPI.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    t : HttpResult<List<Banner>> ->
                    t.data?.let {
                        bgabanner.setAdapter { banner, itemView, model, position ->
                            context?.let {
                                Glide.with(it)
                                        .load(model)
                                        .into(itemView as ImageView)
                            }
                        }
                        bgabanner.setData(it.map { it.imagePath }, it.map { it.title })
                    }
                },{
                    error ->
                    error.printStackTrace()
                },{
                    Logger.d("onComplete")
                },{
                    Logger.d("onStart")
                })
    }

}