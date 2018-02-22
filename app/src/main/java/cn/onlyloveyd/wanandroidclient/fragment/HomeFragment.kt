package cn.onlyloveyd.wanandroidclient.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.onlyloveyd.wanandroidclient.R
import kotlinx.android.synthetic.main.activity_home.*
import me.yokeyword.fragmentation.SupportFragment

/**
 * 文 件 名: HomeFragment
 * 创建日期: 2018/2/11 07:54
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class HomeFragment : SupportFragment() {
    companion object {
        internal fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val fragments = mutableListOf<Fragment>()
    private var lastShowFragment = 0


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (lastShowFragment != 0) {
                    switchFragment(lastShowFragment, 0)
                    lastShowFragment = 0
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (lastShowFragment != 1) {
                    switchFragment(lastShowFragment, 1)
                    lastShowFragment = 1
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_hot -> {
                if (lastShowFragment != 2) {
                    switchFragment(lastShowFragment, 2)
                    lastShowFragment = 2
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                if (lastShowFragment != 3) {
                    switchFragment(lastShowFragment, 3)
                    lastShowFragment = 3
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_home, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val states = arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked))
        val colors = intArrayOf(resources.getColor(R.color.tab_unchecked), resources.getColor(R.color.tab_checked))
        val csl = ColorStateList(states, colors)
        navigation.itemTextColor = csl
        navigation.itemIconTintList = csl

        initFragments()
    }


    private fun initFragments() {
        val articleFragment = ArticleFragment()
        val knowledgeTreeFragment = KnowledgeTreeFragment()
        val hotFragment = HotFragment()
        val meFragment = MeFragment()
        fragments.add(articleFragment)
        fragments.add(knowledgeTreeFragment)
        fragments.add(hotFragment)
        fragments.add(meFragment)
        lastShowFragment = 0
        fragmentManager?.beginTransaction()?.add(R.id.contentPanel, articleFragment)?.show(articleFragment)?.commit()
    }

    private fun switchFragment(lastIndex: Int, index: Int) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.let {
            it.hide(fragments[lastIndex])
            if (!fragments[index].isAdded) {
                it.add(R.id.contentPanel, fragments[index])
            }
            it.show(fragments[index]).commitAllowingStateLoss()
        }
    }
}