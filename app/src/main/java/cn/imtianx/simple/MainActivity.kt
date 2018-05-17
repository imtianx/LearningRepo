package cn.imtianx.simple

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.imtianx.simple.ui.DataBindingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_data_binding.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                    DataBindingActivity::class.java))
        }
    }
}
