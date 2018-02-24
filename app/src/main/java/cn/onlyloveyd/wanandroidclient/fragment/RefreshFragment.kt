package cn.onlyloveyd.wanandroidclient.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * 文 件 名: RefreshFragment
 * 创建日期: 2018/2/24 15:03
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
abstract class RefreshFragment<T>: Fragment(), BGARefreshLayout.BGARefreshLayoutDelegate {
    var index = 0
    var pageCount = 0
    val datas = mutableListOf<T>()

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

    abstract fun initRvContent()

    private fun initBGAData() {
        bgarefreshlayout.beginRefreshing()
    }

    abstract override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        getData(0)
    }

    abstract fun getData(pageNum: Int = -1)
}