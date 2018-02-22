package cn.onlyloveyd.wanandroidclient.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.activity.LoginActivity
import cn.onlyloveyd.wanandroidclient.ext.Ext
import cn.onlyloveyd.wanandroidclient.ext.Preference
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_me.*
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.support.v4.startActivityForResult


/**
 * 文 件 名: MeFragment
 * 创建日期: 2018/2/8 11:06
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class MeFragment : SupportFragment() {

    private val isLogin: Boolean by Preference(Ext.PREF_IS_LOGIN, false)
    private val username: String by Preference(Ext.PREF_USERNAME, "")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_me, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        avatar_layout.setOnClickListener { _ ->
            startActivityForResult<LoginActivity>(Ext.LOGIN_REQUEST_CODE)
        }

        collections_layout.setOnClickListener { _->
            Retrofitance.wanAndroidAPI.getCollections().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe ( {
                        it->
                        System.err.println("yidong -- result = " + it.string())
                    },{
                        it->
                        it.printStackTrace()
                    } )
        }

        exit_layout.setOnClickListener { _ ->
            val dialog = AlertDialog.Builder(context,R.style.AppTheme_Dark_Dialog)
            dialog.setMessage("确定退出嘛?")
            dialog.setPositiveButton("确定", { _, _ ->
                Preference.clear()
                tv_username.text = getString(R.string.logout)
                avatar_layout.isClickable = true
                exit_layout.visibility = View.GONE
            })
            dialog.setNegativeButton("取消", { dialog, _ ->
                dialog.dismiss()
            })
                    .show()


        }

        about_layout.setOnClickListener { _->

        }
    }

    override fun onResume() {
        super.onResume()
        if(isLogin){
            avatar_layout.isClickable =false
            exit_layout.visibility = View.VISIBLE
            tv_username.text = username
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Ext.LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            System.err.println("yidong -- resultCode = " + resultCode)
            System.err.println("yidong -- intent = " + data?.extras.toString())

            val username = data?.getStringExtra("username")
            tv_username.text= username
            avatar_layout.isClickable = false
            exit_layout.visibility = View.VISIBLE
        }
    }
}