package cn.imtianx.mdsimple.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import cn.imtianx.mdsimple.R
import com.gyf.barlibrary.ImmersionBar

/**
 * <pre>
 *     @desc: base activity
 * </pre>
 * @author 奚岩
 * @date 2018/5/29 下午3:24
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var mImmersionBar: ImmersionBar
    var isStatusBarDarkFont = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mImmersionBar = ImmersionBar.with(this)
        initWindows()

        initToolbar(findViewById(R.id.toolbar))

        initWidget()

        initData()

    }

    private fun initToolbar(toolbar: Toolbar?) {
        toolbar?.let {
            setSupportActionBar(it)
            if (isShowBack) {
                supportActionBar?.run {
                    setDisplayHomeAsUpEnabled(true)
                    setHomeButtonEnabled(true)
                }
            }
        }

        mImmersionBar.apply {
            if (isStatusBarDarkFont) {
                statusBarDarkFont(true, 0.2f)
            }
            statusBarColor(R.color.colorPrimary)
        }.init()
    }


    protected var isShowBack = true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                false
            }
        }
    }

    abstract fun getContentLayoutId(): Int

    protected open fun initWindows() {
        setContentView(getContentLayoutId())
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar.destroy()
    }

    protected open fun initData() {
    }

    protected open fun initWidget() {
    }

}