package cn.imtianx.mdsimple.appbar

import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.view.View
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity
import kotlinx.android.synthetic.main.activity_appbar_collapsing.*


/**
 * <pre>
 *     @desc: appbar collapsing
 * </pre>
 * @author 奚岩
 * @date 2018/8/1 20:09 PM
 */
class AppbarCollapsingActivity : BaseActivity() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_appbar_collapsing
    }

    override fun initWidget() {
        super.initWidget()
        mImmersionBar.transparentStatusBar().titleBar(toolbar).init()
    }

    fun fabClickEvent(view: View) {
        Snackbar.make(view, "fab is clicked", Snackbar.LENGTH_SHORT).show()
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, AppbarCollapsingActivity::class.java))
        }
    }
}