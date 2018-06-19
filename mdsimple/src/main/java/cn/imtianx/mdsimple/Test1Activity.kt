package cn.imtianx.mdsimple

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.RelativeLayout
import cn.imtianx.mdsimple.databinding.ActivityTest1Binding
import kotlinx.android.synthetic.main.activity_test_1.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/6/14 8:36 PM
 */
class Test1Activity : AppCompatActivity() {
    private val datas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityTest1Binding>(this, R.layout.activity_test_1)
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

        val lp = fab.layoutParams as RelativeLayout.LayoutParams
        fab.animate()
                .translationY((fab.height + lp.bottomMargin).toFloat()).interpolator = AccelerateInterpolator(3.0f)

    }

    private fun showFab() {
        fab.animate()
                .translationY(0f).interpolator = DecelerateInterpolator(3.0f)

        toolbar.animate()
                .translationY(0f).interpolator = DecelerateInterpolator(3.0f)

    }
}