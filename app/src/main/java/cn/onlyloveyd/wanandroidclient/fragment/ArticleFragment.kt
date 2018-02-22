package cn.onlyloveyd.wanandroidclient.fragment

import android.os.Bundle
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
import me.yokeyword.fragmentation.SupportFragment

/**
 * 文 件 名: ArticleFragment
 * 创 建 人: 易冬
 * 创建日期: 2018/2/6 20:36
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
class ArticleFragment : SupportFragment(), BGARefreshLayout.BGARefreshLayoutDelegate {
    private var index = 0
    private var pageCount = 0
    private val datas = mutableListOf<Article>()

    private val articleAdapter: ArticlesAdapter by lazy {
        ArticlesAdapter(context, datas)
    }
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_article, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initBGALayout()
        initRvContent()
        initBGAData()
    }

    private fun initBGALayout() {
        // 为BGARefreshLayout 设置代理
        bgarefreshlayout.setDelegate(this)
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能

        val refreshViewHolder = BGANormalRefreshViewHolder(context, true)
        refreshViewHolder.setLoadingMoreText(getString(R.string.load_more))
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.white)
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.white)
        bgarefreshlayout.setRefreshViewHolder(refreshViewHolder)
    }

    private fun initRvContent() {
        rv_content.layoutManager = linearLayoutManager
        rv_content.adapter = articleAdapter
    }

    private fun initBGAData() {
        bgarefreshlayout.beginRefreshing()
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        if (pageCount != 0 && index > pageCount) {
            return false
        }
        getArticles(++index)
        return true
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        getArticles(0)
    }

    private fun getArticles(pageNum: Int) {
        Retrofitance.wanAndroidAPI.getArticles(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: HttpResult<ArticleResponseBody> ->
                    Logger.d("t = " + t.data.toString())
                    if (pageNum == 0) {
                        pageCount = t.data.pageCount
                    }
                    t.data.datas.let {
                        articleAdapter.run {
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
                }, {
                    Logger.d("onComplete")
                }, {
                    Logger.d("onStart")
                })

    }
}