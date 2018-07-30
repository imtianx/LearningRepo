package cn.imtianx.mdsimple.navigation

import android.content.Context
import android.content.Intent
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseDataBindingActivity
import cn.imtianx.mdsimple.databinding.ActivityNavigationBinding
import kotlinx.android.synthetic.main.activity_navigation.*

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

        navigation_view.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navi_home -> {
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

    companion object {
        fun launch(context: Context) {
            context.apply {
                startActivity(Intent(this, NavigationActivity::class.java))
            }
        }
    }

}