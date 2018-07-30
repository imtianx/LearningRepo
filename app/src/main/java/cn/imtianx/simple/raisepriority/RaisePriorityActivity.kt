package cn.imtianx.simple.raisepriority

import android.content.Context
import android.content.Intent
import cn.imtianx.jetpacklearning.common.base.BaseActivity
import cn.imtianx.simple.R

/**
 * <pre>
 *     @desc:提升进程优先级
 * </pre>
 * @author 奚岩
 * @date 2018/7/30 11:22 AM
 */
class RaisePriorityActivity : BaseActivity() {
    override fun getContentLayoutId(): Int {
        return R.layout.activity_raise_priority
    }

    override fun initData() {
        super.initData()
        startService(Intent(this, NormalService::class.java))
        startService(Intent(this, HighPriorityService::class.java))
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, RaisePriorityActivity::class.java))
        }
    }
}