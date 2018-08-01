package cn.imtianx.mdsimple.appbar

import android.content.Context
import android.content.Intent
import android.support.design.widget.AppBarLayout
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity
import kotlinx.android.synthetic.main.activity_appbar.*

/**
 * <pre>
 *     @desc: appbar
 * </pre>
 * @author 奚岩
 * @date 2018/8/1 3:09 PM
 */
class AppbarActivity : BaseActivity() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_appbar
    }

    override fun initWidget() {
        super.initWidget()


        //  app:layout_scrollFlags="scroll"
//        setToolbarScrollFlag(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL)

        //  app:layout_scrollFlags="scroll|enterAlways"
        setToolbarScrollFlag(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL xor
                AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)

    }

    private fun setToolbarScrollFlag(scrollFlag: Int) {
        (toolbar.layoutParams as AppBarLayout.LayoutParams).apply {
            scrollFlags = scrollFlag
        }
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, AppbarActivity::class.java))
        }
    }
}