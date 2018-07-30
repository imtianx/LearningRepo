package cn.imtianx.mdsimple.behavior

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseDataBindingActivity
import cn.imtianx.mdsimple.databinding.ActivityFabScrollBinding
import kotlinx.android.synthetic.main.activity_fab_scroll.*

/**
 * <pre>
 *     @desc: 隐藏behavior--滚动监听
 * </pre>
 * @author 奚岩
 * @date 2018/6/14 8:36 PM
 */
class FabScrollListenerActivity : BaseDataBindingActivity<ActivityFabScrollBinding>() {

    private val datas = mutableListOf<String>()

    override fun getContentLayoutId(): Int {
        return R.layout.activity_fab_scroll
    }

    override fun initWidget() {
        super.initWidget()
        setSupportActionBar(toolbar)
        title = "fab滚动-scroll listener"


        for (i in 0..40) {
            datas.add("item No.$i")
        }

        rv_test_1.layoutManager = LinearLayoutManager(this)
        rv_test_1.adapter = Test1Adapter(datas)

        rv_test_1.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            val THRESGOLD: Int = 20
            var distance: Int = 0
            var isVisable = true


            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (distance > THRESGOLD && isVisable) {
                    hideFab()
                    isVisable = false
                    distance = 0
                } else if (distance < -THRESGOLD && !isVisable) {
                    isVisable = true
                    showFab()
                    distance = 0
                }
                if ((isVisable && dy > 0) || (!isVisable && dy < 0))
                    distance += dy
            }
        })

    }

    private fun hideFab() {

        toolbar.animate()
                .translationY((-fab.height).toFloat()).interpolator = AccelerateInterpolator(3.0f)

        // fab scale x and y
        fab.animate().scaleX(0f).scaleY(0f).start()

    }

    private fun showFab() {

        toolbar.animate()
                .translationY(0f).interpolator = DecelerateInterpolator(3.0f)

        // fab scale x and y
        fab.animate().scaleX(1f).scaleY(1f).start()

    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, FabScrollListenerActivity::class.java))
        }
    }
}