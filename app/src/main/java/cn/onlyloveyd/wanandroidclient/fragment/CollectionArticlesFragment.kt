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
import cn.onlyloveyd.wanandroidclient.adapter.CollectionArticlesAdapter
import cn.onlyloveyd.wanandroidclient.bean.CollectionArticle
import cn.onlyloveyd.wanandroidclient.bean.CollectionResponseBody
import cn.onlyloveyd.wanandroidclient.bean.HttpResult
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * 文 件 名: CollectionArticlesFragment
 * 创建日期: 2018/2/22 11:25
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
open class CollectionArticlesFragment : RefreshFragment<CollectionArticle>(), BGARefreshLayout.BGARefreshLayoutDelegate {

    val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    val adapter by lazy {
        CollectionArticlesAdapter(context, datas)
    }

    override fun initRvContent() {
        rv_content.layoutManager = linearLayoutManager
        rv_content.adapter = adapter
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        if (pageCount != 0 && index > pageCount) {
            index--
            return false
        }
        getData(++index)
        return true
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        getData(0)
    }

    override fun getData(pageNum: Int) {
        Retrofitance.wanAndroidAPI.getCollectionArticles(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: HttpResult<CollectionResponseBody<CollectionArticle>> ->
                    Logger.d("t = " + t.data.toString())
                    if (pageNum == 0) {
                        pageCount = t.data.pageCount
                    }
                    t.data.datas.let {
                        adapter.run {
                            if (bgarefreshlayout.currentRefreshStatus == BGARefreshLayout.RefreshStatus.REFRESHING) {
                                replaceData(it)
                                bgarefreshlayout.endRefreshing()
                            }
                            if (bgarefreshlayout.isLoadingMore) {
                                addData(it)
                                bgarefreshlayout.endLoadingMore()
                            }
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