package cn.onlyloveyd.wanandroidclient.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.onlyloveyd.wanandroidclient.R
import me.yokeyword.fragmentation.SupportFragment

/**
 * 文 件 名: ArticleFragment
 * 创 建 人: 易冬
 * 创建日期: 2018/2/6 20:36
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
class ArticleFragment : SupportFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_article, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val llm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}