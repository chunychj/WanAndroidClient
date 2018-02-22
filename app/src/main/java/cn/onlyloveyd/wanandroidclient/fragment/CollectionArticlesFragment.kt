package cn.onlyloveyd.wanandroidclient.fragment

import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.onlyloveyd.wanandroidclient.bean.ArticleResponseBody
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
class CollectionArticlesFragment : ArticleFragment() {

    override fun getArticles(pageNum: Int) {
        Retrofitance.wanAndroidAPI.getCollectionArticles(pageNum)
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
                    bgarefreshlayout.endRefreshing()
                    bgarefreshlayout.endLoadingMore()
                }, {
                    Logger.d("onComplete")

                }, {
                    Logger.d("onStart")
                })

    }
}