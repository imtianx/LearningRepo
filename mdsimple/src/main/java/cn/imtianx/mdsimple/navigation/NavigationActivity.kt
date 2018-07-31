package cn.imtianx.mdsimple.navigation

import android.content.Context
import android.content.Intent
import android.support.v4.view.MenuItemCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseDataBindingActivity
import cn.imtianx.mdsimple.databinding.ActivityNavigationBinding
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlin.math.log

/**
 * <pre>
 *     @desc: 底部导航
 * </pre>
 * @author 奚岩
 * @date 2018/7/30 9:46 PM
 */
class NavigationActivity : BaseDataBindingActivity<ActivityNavigationBinding>() {


    override fun getContentLayoutId(): Int {
        return R.layout.activity_navigation
    }


    override fun initWidget() {
        super.initWidget()

        setSupportActionBar(toolbar)

        Log.e("tx","initWidget")

        NavigationViewHelper.disableShiftingMode(navigation_view)

        navigation_view.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navi_home -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navi_order -> {
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navi_cart -> {
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navi_mine -> {
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.e("tx","onCreateOptionsMenu")
        menu?.let {
            //            menuInflater.inflate(R.menu.menu_toolbar, it)

//            menuInflater.inflate(R.menu.menu_toolbar_text, it)

            menuInflater.inflate(R.menu.menu_toolbar_search, it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

//            R.id.menu_toolbar_add -> {
//                Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show()
//                return true
//            }
//            R.id.menu_toolbar_search -> {
//                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
//                return true
//            }
//            R.id.menu_toolbar_scan -> {
//                Toast.makeText(this, "scan", Toast.LENGTH_SHORT).show()
//                return true
//            }


//            R.id.menu_toolbar_share -> {
//                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()
//                return true
//            }


            R.id.menu_search -> {
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun launch(context: Context) {
            context.apply {
                startActivity(Intent(this, NavigationActivity::class.java))
            }
        }
    }

}