package cn.onlyloveyd.wanandroidclient.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.bean.Article
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

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
                .setText(R.id.tv_article_chapterName, item.chapterName)

        if(!TextUtils.isEmpty(item.envelopePic)) {
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
    }

}