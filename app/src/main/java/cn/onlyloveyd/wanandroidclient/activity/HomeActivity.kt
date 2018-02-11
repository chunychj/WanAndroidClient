package cn.onlyloveyd.wanandroidclient.activity

import android.os.Bundle
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.fragment.HomeFragment
import me.yokeyword.fragmentation.SupportActivity

class HomeActivity : SupportActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commit()
        }
    }
}
