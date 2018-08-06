package cn.imtianx.mdsimple.behavior

import android.content.Context
import android.content.Intent
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity

/**
 * <pre>
 *     @desc: title behavior
 * </pre>
 * @author 奚岩
 * @date 2018/8/6 21:09 PM
 */
class TitleBehaviorActivity : BaseActivity() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_behavior_title
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, TitleBehaviorActivity::class.java))
        }
    }
}