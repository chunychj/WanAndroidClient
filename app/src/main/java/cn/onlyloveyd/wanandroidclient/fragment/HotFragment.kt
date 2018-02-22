package cn.onlyloveyd.wanandroidclient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.adapter.FriendWebsiteAdapter
import cn.onlyloveyd.wanandroidclient.bean.Banner
import cn.onlyloveyd.wanandroidclient.bean.Friend
import cn.onlyloveyd.wanandroidclient.bean.HttpResult
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import com.bumptech.glide.Glide
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_hot.*
import me.yokeyword.fragmentation.SupportFragment


/**
 * 文 件 名: HotFragment
 * 创建日期: 2018/2/8 09:57
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class HotFragment : SupportFragment() {
    private val friendDatas = mutableListOf<Friend>()
    private val friendAdapter by lazy {
        FriendWebsiteAdapter(context, friendDatas)
    }
    private val flexboxlm by lazy {
        FlexboxLayoutManager()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refresh_layout.setOnClickListener { _: View? ->
            getFriends()
        }
        flexboxlm.flexDirection = FlexDirection.ROW
        flexboxlm.flexWrap = FlexWrap.WRAP
        flexboxlm.alignItems = AlignItems.STRETCH
        rv_hot_website.adapter = friendAdapter
        rv_hot_website.layoutManager = flexboxlm

        getBanners()
        getFriends()
    }

    private fun getBanners() {
        Retrofitance.wanAndroidAPI.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: HttpResult<List<Banner>> ->
                    t.data.let {
                        bgabanner.setAdapter { _, itemView, model, _ ->
                            context?.let {
                                Glide.with(it)
                                        .load(model)
                                        .into(itemView as ImageView)
                            }
                        }
                        bgabanner.setData(it.map { it.imagePath }, it.map { it.title })
                    }
                }, { error ->
                    error.printStackTrace()
                }, {
                    Logger.d("onComplete")
                }, {
                    Logger.d("onStart")
                })

    }

    private fun getFriends() {
        animation_view.visibility = View.VISIBLE
        rv_hot_website.visibility = View.GONE
        Retrofitance.wanAndroidAPI.getFriendWebsites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: HttpResult<List<Friend>> ->
                    t.data.let {
                        friendAdapter.run {
                            replaceData(it)
                            loadMoreComplete()
                            loadMoreEnd()
                            setEnableLoadMore(false)
                        }
                    }
                }, { error ->
                    error.printStackTrace()
                }, {
                    Logger.d("onComplete")
                    animation_view.cancelAnimation()
                    animation_view.visibility = View.GONE
                    rv_hot_website.visibility = View.VISIBLE
                }, {
                    Logger.d("onStart")
                })
        animation_view.playAnimation()
    }

}