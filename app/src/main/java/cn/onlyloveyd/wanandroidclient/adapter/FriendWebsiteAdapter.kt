package cn.onlyloveyd.wanandroidclient.adapter

import android.content.Context
import android.widget.TextView
import cn.onlyloveyd.Ext
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.bean.Friend
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import org.jetbrains.anko.backgroundResource


/**
 * 文 件 名: FriendWebsiteAdapter
 * 创建日期: 2018/2/9 15:01
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class FriendWebsiteAdapter(private val context: Context?, datas: MutableList<Friend>) : BaseQuickAdapter<Friend, BaseViewHolder>(R.layout.rv_item_friend, datas) {
    override fun convert(helper: BaseViewHolder, item: Friend?) {
        item ?: return
        (helper.getView<TextView>(R.id.tv_friend_website)).let {
            it.backgroundResource = Ext.HollowBackgroundRepository[helper.adapterPosition % Ext.HollowBackgroundRepository.size]
            it.text = item.name

            val lp = it.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                val flexboxlp = it.layoutParams as FlexboxLayoutManager.LayoutParams
                flexboxlp.flexGrow = 1.0f
            }
        }
    }
}