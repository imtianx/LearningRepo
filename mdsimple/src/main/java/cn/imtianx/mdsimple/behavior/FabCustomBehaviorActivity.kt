package cn.imtianx.mdsimple.behavior

import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseDataBindingActivity
import cn.imtianx.mdsimple.databinding.ActivityFabBehaviorBinding
import kotlinx.android.synthetic.main.activity_fab_behavior.*

/**
 * <pre>
 *     @desc: 隐藏 fab --自定义behavior
 * </pre>
 * @author 奚岩
 * @date 2018/6/14 11:20 PM
 */
class FabCustomBehaviorActivity : BaseDataBindingActivity<ActivityFabBehaviorBinding>() {

    private val datas = mutableListOf<String>()

    override fun getContentLayoutId(): Int {
        return R.layout.activity_fab_behavior
    }

    override fun initWidget() {
        super.initWidget()
        setSupportActionBar(toolbar)
        title = "fab滚动-behavior"


        for (i in 0..40) {
            datas.add("item No.$i")
        }

        rv_test_2.layoutManager = LinearLayoutManager(this)
        rv_test_2.adapter = FabAdapter(datas)

    }

    fun fabClickEvent(view: View) {
        Snackbar.make(view, "fab is clicked", Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, FabCustomBehaviorActivity::class.java))
        }
    }

}