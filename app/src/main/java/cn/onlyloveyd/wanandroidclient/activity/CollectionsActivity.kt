package cn.onlyloveyd.wanandroidclient.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.ext.Ext
import cn.onlyloveyd.wanandroidclient.ext.Preference
import cn.onlyloveyd.wanandroidclient.fragment.CollectionArticlesFragment
import kotlinx.android.synthetic.main.activity_collections.*
import java.util.*

/**
 * 文 件 名: CollectionsActivity
 * 创建日期: 2018/2/11 11:08
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class CollectionsActivity : AppCompatActivity() {
    private val mFragments = ArrayList<Fragment>()
    private val mCollectionsSegmentAdapter by lazy {
        CollectionsPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections)

        mFragments.add(CollectionArticlesFragment())
        mFragments.add(CollectionArticlesFragment())

        collections_segment_layout.setTabData(Ext.COLLECTIONS_SEGMENT_TITLES)
        bgaviewpager.adapter = mCollectionsSegmentAdapter
        bgaviewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                collections_segment_layout.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        bgaviewpager.currentItem = 0

        toolbar.setNavigationOnClickListener { _ ->
            this@CollectionsActivity.finish()
        }
    }

    private inner class CollectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return Ext.COLLECTIONS_SEGMENT_TITLES[position]
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }
    }
}