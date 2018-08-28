package cn.imtianx.mdsimple.status

import android.content.Context
import android.content.Intent
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.appbar.AppbarCollapsingActivity
import cn.imtianx.mdsimple.base.BaseActivity
import kotlinx.android.synthetic.main.activity_status_bar_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * <pre>
 *     @desc: status bar
 * </pre>
 * @author 奚岩
 * @date 2018/8/17 9:37 AM
 */
class StatusBarMainActivity : BaseActivity() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_status_bar_main
    }

    override fun initWidget() {
        super.initWidget()
        btn_status_img.onClick {
            ImageActivity.launch(this@StatusBarMainActivity)
        }

        btn_status_appbar.onClick {
            AppbarCollapsingActivity.launch(this@StatusBarMainActivity)
        }

        btn_status_tab.onClick {
            StatusTabActivity.launch(this@StatusBarMainActivity)
        }
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, StatusBarMainActivity::class.java))
        }
    }
}