package cn.onlyloveyd.wanandroidclient.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.fragment.KnowledgeFragment
import kotlinx.android.synthetic.main.activity_knowledge.*

/**
 * 文 件 名: KnowledageActivity
 * 创建日期: 2018/2/24 16:00
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class KnowledgeActivity : AppCompatActivity() {

    private val names by lazy {
        intent.getStringArrayListExtra("NAME")
    }
    private val cids by lazy {
        intent.getIntegerArrayListExtra("CID")
    }

    private var mFragments:List<KnowledgeFragment> ?= null

    private val knowledgePagerAdapter by lazy {
        KnowledgePagerAdapter(supportFragmentManager)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge)
        initFragments()
        bgaviewpager.adapter = knowledgePagerAdapter
        stl_knowledge.setViewPager(bgaviewpager)

        toolbar.setNavigationOnClickListener { _ ->
            this@KnowledgeActivity.finish()
        }

    }

    private fun initFragments(){
        mFragments = cids.map { KnowledgeFragment.newInstance(it) }
    }

    private inner class KnowledgePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return mFragments?.size!!
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return names[position]
        }

        override fun getItem(position: Int): Fragment {
            return mFragments?.get(position) as KnowledgeFragment
        }

    }

}
