package cn.imtianx.mdsimple.appbar

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.design.widget.Snackbar
import android.view.View
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity


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

    override fun initWindows() {
        super.initWindows()
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT

        }
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