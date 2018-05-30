package cn.imtianx.simple

import android.content.Intent
import cn.imtianx.jetpacklearning.common.base.BaseDataBindingActivity
import cn.imtianx.simple.databinding.ActivityMainBinding
import cn.imtianx.simple.ui.databinding.DataBindingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseDataBindingActivity<ActivityMainBinding>() {
    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initWidget() {
        super.initWidget()
        btn_data_binding.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                    DataBindingActivity::class.java))
        }
    }

}
