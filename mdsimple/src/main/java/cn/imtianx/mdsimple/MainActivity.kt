package cn.imtianx.mdsimple

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.imtianx.mdsimple.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        btn_1.onClick {
            startActivity<Test1Activity>()
        }

        btn_2.onClick {
            startActivity<Test2Activity>()
        }
    }
}
