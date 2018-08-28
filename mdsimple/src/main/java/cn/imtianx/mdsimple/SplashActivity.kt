package cn.imtianx.mdsimple

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.animation.Animation
import cn.imtianx.mdsimple.base.BaseActivity
import com.gyf.barlibrary.BarHide
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/8/16 1:26 PM
 */
class SplashActivity : BaseActivity() {

    private lateinit var objectAnimator: ObjectAnimator
    override fun getContentLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initWindows() {
        super.initWindows()
//        setFullscreen()
    }

    override fun initWidget() {
        super.initWidget()

        mImmersionBar.transparentStatusBar()
                .fullScreen(true)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init()

        objectAnimator = ObjectAnimator.ofFloat(ll_splash, "alpha", 0f, 1f)
                .apply {
                    duration = 2000
                    addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                            finish()
                        }
                    })
                    start()
                }
    }

    override fun onBackPressed() {
        Log.e("tx", "back--------")
        objectAnimator.cancel()
        super.onBackPressed()
    }

    private fun setFullscreen(isHideNavigation: Boolean = true) {
        var flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE

        if (isHideNavigation) {
            flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
        window.decorView.systemUiVisibility = flag
    }

}