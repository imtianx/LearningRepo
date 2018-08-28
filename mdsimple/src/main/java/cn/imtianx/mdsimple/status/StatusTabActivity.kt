package cn.imtianx.mdsimple.status

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity
import kotlinx.android.synthetic.main.activity_status_bar_tab.*
import kotlinx.android.synthetic.main.fragment_tab_one.*
import kotlinx.android.synthetic.main.fragment_tab_three.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/8/27 3:11 PM
 */
class StatusTabActivity : BaseActivity() {

    private val mFragments = mutableListOf<Fragment>()
    private lateinit var mVpAdapter: ViewPagerFragmentAdapter
    private var menuItem: MenuItem? = null

    override fun getContentLayoutId(): Int {
        return R.layout.activity_status_bar_tab
    }

    override fun initWidget() {
        super.initWidget()
        mFragments.clear()
        mFragments.add(0, TabOneFragment.newInstance())
        mFragments.add(1, TabTwoFragment.newInstance())
        mFragments.add(2, TabThreeFragment.newInstance())

        mVpAdapter = ViewPagerFragmentAdapter(supportFragmentManager, mFragments)
        container.apply {
            adapter = mVpAdapter
            offscreenPageLimit = 3
            currentItem = 0
            updateStatusBar()
        }

        navigation_view.setOnNavigationItemSelectedListener { item ->
            val position = when (item.itemId) {
                R.id.navi_cart -> {
                    1
                }
                R.id.navi_mine -> {
                    2
                }
                else -> {
                    0
                }
            }
            if (container.currentItem != position) {
                container.currentItem = position
                updateStatusBar(position)
            }
            true
        }

        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

                if (menuItem != null) {
                    menuItem!!.isChecked = false
                } else {
                    navigation_view.menu.getItem(0).isChecked = false
                }
                menuItem = navigation_view.menu.getItem(position)
                menuItem!!.isChecked = true
                updateStatusBar(position)
            }
        })
    }

    private fun updateStatusBar(position: Int = 0) {
        when (position) {
            1 -> {
                mImmersionBar.statusBarColor(R.color.colorPrimary)
                        .statusBarDarkFont(false)
                        .init()
            }
            2 -> {
                mImmersionBar.statusBarDarkFont(true)
                        .transparentStatusBar()
                        .init()
            }
            else -> {
                mImmersionBar.statusBarDarkFont(true)
                        .fitsSystemWindows(false)
                        .transparentStatusBar()
                        .init()
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.menu_navigation_tab, it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, StatusTabActivity::class.java))
        }
    }
}