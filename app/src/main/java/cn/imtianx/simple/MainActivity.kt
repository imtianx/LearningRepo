package cn.imtianx.simple

import android.arch.lifecycle.Observer
import android.content.Intent
import android.util.Log
import cn.imtianx.common.net.resp.applyActionWithNetworkData
import cn.imtianx.jetpacklearning.common.base.BaseDataBindingActivity
import cn.imtianx.jetpacklearning.common.extentions.getViewModel
import cn.imtianx.simple.databinding.ActivityMainBinding
import cn.imtianx.simple.ui.databinding.DataBindingActivity
import cn.imtianx.simple.ui.https.TestHttpsActivity
import cn.imtianx.simple.ui.test.TextViewSpanActivity
import cn.imtianx.simple.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : BaseDataBindingActivity<ActivityMainBinding>() {

    private lateinit var exectors: ThreadPoolExecutor

    private val viewModel: MainViewModel by lazy {
        getViewModel(MainViewModel::class.java)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initWidget() {
        super.initWidget()


        btn_data_binding.onClick {
            startActivity(
                Intent(
                    this@MainActivity,
                    DataBindingActivity::class.java
                )
            )
        }

        btn_retrofit_timeout.onClick {
            initThread()
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

        btn_coroutine.onClick {
            exectors.shutdownNow()
        }

        btn_tv_span.setOnClickListener {
            TextViewSpanActivity.launch(this@MainActivity)
        }

        btn_https.setOnClickListener {
            TestHttpsActivity.launch(this@MainActivity)
        }
    }


    private fun testCoroutine() {

        val job1 = launch(CommonPool, CoroutineStart.LAZY) {
            println("job1---------currentThreadId:${Thread.currentThread().id}")
            var count = 0
            while (true) {
                count++
                delay(500)
                println("count--------: $count")
            }
        }

        val job2 = async(CommonPool) {
            println("job2-------currentThreadId:${Thread.currentThread().id}")
            job1.start()
            "job2         currentThreadId:${Thread.currentThread().id}"
        }

        launch(UI) {
            println("ui------------currentThreadId:${Thread.currentThread().id}")
            delay(3000)
            job1.cancel()
            println(job2.await())

        }
    }

    private fun initThread() {
        exectors = ThreadPoolExecutor(
            1, 1, 10, TimeUnit.MILLISECONDS,
            LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),
            ThreadPoolExecutor.AbortPolicy()
        )


        exectors.execute(CRunnable())
        exectors.execute(CRunnable())
    }

}


class CRunnable : Runnable {
    override fun run() {
        println("ThreadOne id : ${Thread.currentThread().id}")
        Thread.sleep(3000)
        println("ThreadOne  执行完成")
    }
}
