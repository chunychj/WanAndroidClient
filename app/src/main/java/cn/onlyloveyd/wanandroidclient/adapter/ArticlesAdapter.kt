package cn.onlyloveyd.wanandroidclient.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.activity.WebActivity
import cn.onlyloveyd.wanandroidclient.bean.Article
import cn.onlyloveyd.wanandroidclient.ext.Ext
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.share
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 文 件 名: ArticlesAdapter
 * 创 建 人: 易冬
 * 创建日期: 2018/2/6 21:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
class ArticlesAdapter(private val context: Context?, datas: MutableList<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.rv_item_article, datas) {

    override fun convert(helper: BaseViewHolder, item: Article?) {
        item ?: return
        helper.setText(R.id.tv_article_title, item.title)
                .setText(R.id.tv_article_date, item.niceDate)
                .setText(R.id.tv_article_author, item.author)

        if (!TextUtils.isEmpty(item.chapterName)) {
            helper.setText(R.id.tv_article_chapterName, item.chapterName)
            helper.getView<TextView>(R.id.tv_article_chapterName).visibility = View.VISIBLE
        } else {
            helper.getView<TextView>(R.id.tv_article_chapterName).visibility = View.INVISIBLE
        }

        if (!TextUtils.isEmpty(item.envelopePic)) {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                    .visibility = View.VISIBLE
            context?.let {
                Glide.with(it)
                        .load(item.envelopePic)
                        .into(helper.getView(R.id.iv_article_thumbnail))
            }
        } else {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                    .visibility = View.GONE
        }

        helper.getView<ImageView>(R.id.iv_like).let {
            if (item.collect) {
                it.setImageResource(R.drawable.ic_favorite)
            } else {
                it.setImageResource(R.drawable.ic_unfavorite)
            }

            it.setOnClickListener { _ ->
                it.isEnabled = false
                if (item.collect) {
                    Retrofitance.wanAndroidAPI.cancelCollectionArticle(item.id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ body ->
                                if (body.errorCode > Ext.HTTP_ERROR) {
                                    context?.toast("取消收藏成功")
                                    it.setImageResource(R.drawable.ic_unfavorite)
                                    item.collect = false
                                } else {
                                    context?.toast("取消收藏失败 ${body.errorMsg}")
                                }
                            }, { error ->
                                error.printStackTrace()
                                context?.toast("取消收藏失败 ${error.message}")
                                it.isEnabled = true
                            }, {
                                it.isEnabled = true
                            })
                } else {
                    Retrofitance.wanAndroidAPI.collectInstationArticle(item.id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ body ->
                                if (body.errorCode > Ext.HTTP_ERROR) {
                                    context?.toast("收藏成功")
                                    it.setImageResource(R.drawable.ic_favorite)
                                    item.collect = true
                                } else {
                                    context?.toast("收藏失败 ${body.errorMsg}")
                                }
                            }, { error ->
                                error.printStackTrace()
                                context?.toast("收藏失败 ${error.message}")
                                it.isEnabled = true
                            }, {
                                it.isEnabled = true
                            })
                }
            }
        }

        helper.itemView.setOnClickListener { _ ->
            context?.startActivity<WebActivity>("URL" to item.link)
        }


        helper.getView<ImageView>(R.id.iv_like)
        helper.getView<ImageView>(R.id.iv_share).setOnClickListener {
            context?.share(item.link)
        }
    }

}