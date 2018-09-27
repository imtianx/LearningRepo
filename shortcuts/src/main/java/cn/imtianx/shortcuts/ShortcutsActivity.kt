package cn.imtianx.shortcuts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/9/18 10:17 AM
 */
class ShortcutsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shortcuts_one)
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, ShortcutsActivity::class.java))
        }
    }
}