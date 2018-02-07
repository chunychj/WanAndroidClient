package cn.onlyloveyd.wanandroidclient.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.bean.Article
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
class ArticlesAdapter(val context: Context, datas: MutableList<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.rv_item_article, datas){

    override fun convert(helper: BaseViewHolder?, item: Article?) {
    }

}