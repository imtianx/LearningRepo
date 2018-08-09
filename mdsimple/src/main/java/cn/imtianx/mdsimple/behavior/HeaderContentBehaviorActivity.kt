package cn.imtianx.mdsimple.behavior

import android.content.Context
import android.content.Intent
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/8/9 5:57 PM
 */
class HeaderContentBehaviorActivity : BaseActivity() {

    override fun getContentLayoutId() = R.layout.activity_header_content_behavior


    companion object {

        fun launch(context: Context) {
            context.startActivity(Intent(context, HeaderContentBehaviorActivity::class.java))
        }
    }

}