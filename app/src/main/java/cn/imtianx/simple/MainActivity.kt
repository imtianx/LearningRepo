package cn.imtianx.simple

import android.arch.lifecycle.Observer
import android.content.Intent
import android.util.Log
import cn.imtianx.common.net.resp.applyActionWithNetworkData
import cn.imtianx.jetpacklearning.common.base.BaseDataBindingActivity
import cn.imtianx.jetpacklearning.common.extentions.getViewModel
import cn.imtianx.simple.databinding.ActivityMainBinding
import cn.imtianx.simple.ui.databinding.DataBindingActivity
import cn.imtianx.simple.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : BaseDataBindingActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by lazy {
        getViewModel(MainViewModel::class.java)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initWidget() {
        super.initWidget()
        btn_data_binding.onClick {
            startActivity(Intent(this@MainActivity,
                    DataBindingActivity::class.java))
        }

        btn_retrofit_timeout.onClick {
            viewModel.fetchTimeout()
            Log.e("tx", "当前时间:" + System.currentTimeMillis())
        }

        viewModel.timeOutResult.observe(this@MainActivity, Observer {
            it?.applyActionWithNetworkData(
                    {
                        Log.e("tx", "时间：${System.currentTimeMillis()}    success: ${it}")
                    }, { msg, code ->
                Log.e("tx", "时间：${System.currentTimeMillis()}    error: $msg  code:$code")
            }
            )
        })
    }

}
