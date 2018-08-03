package cn.imtianx.mdsimple.tablayout

import android.content.Context
import android.content.Intent
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity

/**
 * <pre>
 *     @desc: tabLayout
 * </pre>
 * @author 奚岩
 * @date 2018/8/2 11:11 PM
 */
class TabLayoutActivity : BaseActivity() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_tab_layout
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, TabLayoutActivity::class.java))
        }
    }
}