package cn.onlyloveyd.wanandroidclient.adapter

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.widget.TextView
import cn.onlyloveyd.Ext
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.bean.KnowledgeTreeBody
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.FlexboxLayout
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.padding
import org.jetbrains.anko.textColor


/**
 * 文 件 名: KnowledgeTreeAdapter
 * 创建日期: 2018/2/7 10:52
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class KnowledgeTreeAdapter(private val context: Context?, datas: MutableList<KnowledgeTreeBody>) : BaseQuickAdapter<KnowledgeTreeBody, BaseViewHolder>(R.layout.rv_item_knowledge, datas) {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun convert(helper: BaseViewHolder, item: KnowledgeTreeBody?) {
        item ?: return
        helper.setText(R.id.tv_knowledge_parent, item.name)

        val flexlayout = helper.getView<FlexboxLayout>(R.id.flex_knowledge)
        val children = item.children
        if (children.isNotEmpty()) {
            for ((index, value) in children.withIndex()) {
                var textView = TextView(context)
                textView.text = value.name
                textView.textColor = Color.WHITE
                textView.padding = 4
                textView.backgroundDrawable = context?.getDrawable(Ext.BackgroundRepository[index % Ext.BackgroundRepository.size])
                val lp = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)
                lp.setMargins(6, 6, 6, 6)
                flexlayout.addView(textView, lp)
            }
        }
    }
}