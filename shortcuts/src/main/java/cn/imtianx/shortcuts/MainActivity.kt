package cn.imtianx.shortcuts

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var shortcutManager: ShortcutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_shortcut_name.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                shortcutManager = getSystemService(ShortcutManager::class.java)

                val shortcut = ShortcutInfo.Builder(this, "id${System.currentTimeMillis()}")
                        .setShortLabel("shortcut")
                        .setLongLabel("动态添加的shortcut")
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_add_circle_outline_black_24dp))
                        .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("http://imtianx.cn")))
                        .build()

                Log.e("tx", shortcutManager.dynamicShortcuts.toString())

                shortcutManager.dynamicShortcuts = listOf(shortcut)

            } else {
                Toast.makeText(this@MainActivity,
                        "shortcut 在 N 以后支持", Toast.LENGTH_SHORT).show()
            }
        }

        btn_shortcut_list.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                shortcutManager = getSystemService(ShortcutManager::class.java)
                shortcutManager.dynamicShortcuts.forEach { shortcutInfo ->
                    Log.e("tx", shortcutInfo.toString())
                }
            }
        }

    }

}
