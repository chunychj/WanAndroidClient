package cn.onlyloveyd.wanandroidclient

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import cn.onlyloveyd.wanandroidclient.fragment.ArticleFragment
import cn.onlyloveyd.wanandroidclient.fragment.KnowledgeTreeFragment
import kotlinx.android.synthetic.main.activity_home.*
import me.yokeyword.fragmentation.SupportActivity

class HomeActivity : SupportActivity() {
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
            R.id.navigation_notifications -> {
                if (lastShowFragment != 2) {
                    switchFragment(lastShowFragment, 2)
                    lastShowFragment = 2
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        initFragments()
    }


    private fun initFragments() {
        val articleFragment = ArticleFragment()
        val knowledgeTreeFragment = KnowledgeTreeFragment()
        val searchFragment = ArticleFragment()
        fragments.add(articleFragment)
        fragments.add(knowledgeTreeFragment)
        fragments.add(searchFragment)
        lastShowFragment = 0
        supportFragmentManager
                .beginTransaction()
                .add(R.id.contentPanel, articleFragment)
                .show(articleFragment)
                .commit();
    }

    private fun switchFragment(lastIndex: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragments[lastIndex])
        if (!fragments[index].isAdded) {
            transaction.add(R.id.contentPanel, fragments[index])
        }
        transaction.show(fragments[index]).commitAllowingStateLoss()
    }
}
