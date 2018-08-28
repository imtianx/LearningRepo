package cn.imtianx.mdsimple.status

import android.content.Context
import android.content.Intent
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/8/27 11:17 AM
 */
class ImageActivity : BaseActivity() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_image
    }


    override fun initWidget() {
        super.initWidget()
        mImmersionBar.transparentStatusBar().titleBar(iv_bg).init()
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, ImageActivity::class.java))
        }
    }
}