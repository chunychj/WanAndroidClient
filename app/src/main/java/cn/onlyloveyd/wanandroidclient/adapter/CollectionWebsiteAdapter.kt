package cn.onlyloveyd.wanandroidclient.adapter

import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import cn.onlyloveyd.wanandroidclient.R
import cn.onlyloveyd.wanandroidclient.activity.WebActivity
import cn.onlyloveyd.wanandroidclient.bean.CollectionWebsite
import cn.onlyloveyd.wanandroidclient.ext.Ext
import cn.onlyloveyd.wanandroidclient.http.Retrofitance
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 文 件 名: CollectionWebsiteAdapter
 * 创建日期: 2018/2/23 14:32
 * 邮   箱: yidong@gz.csg.cn
 * 描   述：
 * @author Mraz
 */
class CollectionWebsiteAdapter(private val context: Context?, datas: MutableList<CollectionWebsite>) : BaseQuickAdapter<CollectionWebsite, BaseViewHolder>(R.layout.rv_item_website, datas) {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun convert(helper: BaseViewHolder, item: CollectionWebsite?) {
        item ?: return
        helper.setText(R.id.tv_website_name, item.name)

        helper.getView<ImageView>(R.id.iv_like).let {
            it.setImageResource(R.drawable.ic_favorite)
            it.setOnClickListener { _ ->
                it.isEnabled = false
                Retrofitance.wanAndroidAPI.deleteCollectionWebsite(item.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ body ->
                            if (body.errorCode > Ext.HTTP_ERROR) {
                                context?.toast("取消收藏成功")
                                remove(helper.adapterPosition)
                            } else {
                                context?.toast("取消收藏失败 ${body.errorMsg}")
                            }
                        }, { error ->
                            error.printStackTrace()
                            context?.toast("取消收藏失败 ${error.message}")
                            it.isEnabled = true
                        }, {
                            it.isEnabled = true
                        })
            }
        }

        helper.getView<ImageView>(R.id.iv_edit).let {
            it.setOnClickListener { _ ->
                val dialogBuilder = AlertDialog.Builder(context, R.style.AppTheme_Dark_Dialog)
                val dialogContent = LayoutInflater.from(context).inflate(R.layout.dialog_edit_website, null, false)
                dialogBuilder.setView(dialogContent)
                val dialog = dialogBuilder.create()

                val etName = dialogContent.find<EditText>(R.id.et_website_name)
                etName.setText(item.name)
                val etLink = dialogContent.find<EditText>(R.id.et_website_link)
                etLink.setText(item.link)

                val btConfirm = dialogContent.find<Button>(R.id.btn_website_edit_confirm)
                btConfirm.setOnClickListener { _ ->
                    val name = etName.text.toString()
                    val link = etLink.text.toString()
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(link) && Patterns.WEB_URL.matcher(link).matches()) {
                        Retrofitance.wanAndroidAPI.editCollectionWebsite(item.id, name, link)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ it ->
                                    if (it.errorCode > Ext.HTTP_ERROR) {
                                        context?.toast("修改网址成功")
                                        item.name = name
                                        item.link = link
                                        helper.setText(R.id.tv_website_name, item.name)
                                    } else {
                                        context?.toast("修改网址失败 ${it.errorMsg}")
                                    }

                                }, { error ->
                                    context?.toast("修改网址失败 ${error.message}")
                                    dialog.dismiss()
                                }, {
                                    dialog.dismiss()
                                })
                    } else {
                        context?.toast("名称或者网址不符合规范")
                    }

                }
                val btCancel = dialogContent.find<Button>(R.id.btn_website_edit_cancel)
                btCancel.setOnClickListener { _ ->
                    dialog.dismiss()
                }
                dialog.setCancelable(false)
                dialog.show()

            }
        }

        helper.itemView.setOnClickListener { _ ->
            context?.startActivity<WebActivity>("URL" to item.link)
        }

        helper.getView<ImageView>(R.id.iv_like)
        helper.getView<ImageView>(R.id.iv_share).setOnClickListener {

        }

    }
}