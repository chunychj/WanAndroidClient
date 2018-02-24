package cn.onlyloveyd.wanandroidclient.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.adapter.CollectionWebsiteAdapter
import cn.onlyloveyd.wanandroidclient.bean.CollectionWebsite
import cn.onlyloveyd.wanandroidclient.bean.HttpResult
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article.*


/**
 * 文 件 名: CollectionWebsiteFragment
 * 创建日期: 2018/2/23 14:24
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class CollectionWebsiteFragment : RefreshFragment<CollectionWebsite>(), BGARefreshLayout.BGARefreshLayoutDelegate {

    private val adapter: CollectionWebsiteAdapter by lazy {
        CollectionWebsiteAdapter(context, datas)
    }

    override fun initRvContent() {
        super.initRvContent()
        rv_content.adapter = adapter
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return false
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        getData(0)
    }

    override fun getData(pageNum: Int) {
        Retrofitance.wanAndroidAPI.getCollectionWebsites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: HttpResult<MutableList<CollectionWebsite>> ->
                    Logger.d("t = " + t.data.toString())
                    t.data.let {
                        adapter.run {
                            replaceData(it)
                            bgarefreshlayout.endRefreshing()
                        }
                    }
                }, { error ->
                    error.printStackTrace()
                    bgarefreshlayout.endRefreshing()
                    bgarefreshlayout.endLoadingMore()
                }, {
                    Logger.d("onComplete")
                }, {
                    Logger.d("onStart")
                })
    }
}