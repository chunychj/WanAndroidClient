package cn.onlyloveyd.wanandroidclient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.onlyloveyd.wanandroidclient.R
import me.yokeyword.fragmentation.SupportFragment

/**
 * 文 件 名: MeFragment
 * 创建日期: 2018/2/8 11:06
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class MeFragment : SupportFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_me, null, false)
    }
}