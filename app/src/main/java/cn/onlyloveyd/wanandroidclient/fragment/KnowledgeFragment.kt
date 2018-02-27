package cn.onlyloveyd.wanandroidclient.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.onlyloveyd.wanandroidclient.adapter.ArticlesAdapter
import cn.onlyloveyd.wanandroidclient.bean.Article
import cn.onlyloveyd.wanandroidclient.bean.ArticleResponseBody
import cn.onlyloveyd.wanandroidclient.bean.HttpResult
import cn.onlyloveyd.wanandroidclient.bean.Knowledge
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * 文 件 名: KnowledgeFragment
 * 创建日期: 2018/2/24 16:15
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class KnowledgeFragment: RefreshFragment<Article>() {

    companion object {
        private val ARG = "cid"
        fun newInstance(cid: Int): KnowledgeFragment {
            val args = Bundle()
            args.putInt(ARG, cid)
            val fragment = KnowledgeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var adapter:ArticlesAdapter ?=null
    var linearLayoutManager: LinearLayoutManager?= null


    private val cid by lazy {
        arguments?.getInt("cid")
    }

    override fun initRvContent() {
        rv_content.layoutManager = linearLayoutManager
        rv_content.adapter = adapter
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = ArticlesAdapter(context, datas)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        linearLayoutManager = null
        adapter = null
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
        Retrofitance.wanAndroidAPI.getKnowledgeTreeArticles(pageNum, cid!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: HttpResult<ArticleResponseBody> ->
                    Logger.d("t = " + t.data.toString())
                    if (pageNum == 0) {
                        pageCount = t.data.pageCount
                    }
                    t.data.datas.let {
                        adapter?.run {
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