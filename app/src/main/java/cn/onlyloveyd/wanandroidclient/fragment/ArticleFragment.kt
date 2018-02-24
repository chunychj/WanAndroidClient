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
import cn.onlyloveyd.wanandroidclient.adapter.ArticlesAdapter
import cn.onlyloveyd.wanandroidclient.bean.Article
import cn.onlyloveyd.wanandroidclient.bean.ArticleResponseBody
import cn.onlyloveyd.wanandroidclient.bean.HttpResult
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * 文 件 名: ArticleFragment
 * 创 建 人: 易冬
 * 创建日期: 2018/2/6 20:36
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
open class ArticleFragment: RefreshFragment<Article>() {

    val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    val adapter by lazy {
        ArticlesAdapter(context, datas)
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

    override fun getData(pageNum: Int) {
        Retrofitance.wanAndroidAPI.getArticles(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: HttpResult<ArticleResponseBody> ->
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