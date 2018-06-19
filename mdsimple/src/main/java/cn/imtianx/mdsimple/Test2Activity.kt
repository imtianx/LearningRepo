package cn.imtianx.mdsimple

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.imtianx.mdsimple.databinding.ActivityTest2Binding
import kotlinx.android.synthetic.main.activity_test_2.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/6/14 11:20 PM
 */
class Test2Activity : AppCompatActivity() {

    private val datas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityTest2Binding>(this, R.layout.activity_test_2)
        setSupportActionBar(toolbar)
        title = "fab滚动-behavior"


        for (i in 0..40) {
            datas.add("item No.$i")
        }

        rv_test_2.layoutManager = LinearLayoutManager(this)
        rv_test_2.adapter = Test1Adapter(datas)

    }

    fun fabClickEvent(view: View) {
        Snackbar.make(view, "fab is clicked", Snackbar.LENGTH_SHORT).show()
    }

}