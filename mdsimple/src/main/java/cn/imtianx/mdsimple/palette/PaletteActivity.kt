package cn.imtianx.mdsimple.palette

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.view.ViewPager
import android.support.v7.graphics.Palette
import android.widget.ImageView
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity
import kotlinx.android.synthetic.main.activity_palette.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.backgroundColor

/**
 * <pre>
 *     @desc: Palette
 * </pre>
 * @author 奚岩
 * @date 2018/8/4 9:16 AM
 */
class PaletteActivity : BaseActivity() {

    private val bannerList = listOf(R.mipmap.ic_banner_1, R.mipmap.ic_banner_2, R.mipmap.ic_banner_3)

    override fun getContentLayoutId(): Int {
        return R.layout.activity_palette
    }

    override fun initWidget() {
        super.initWidget()

        mImmersionBar
                .titleBar(toolbar)
                .statusBarColor(R.color.colorPrimary)

        banner.setAdapter { _, itemView, model, _ ->
            model?.let {
                (itemView as ImageView).setImageResource((it as Int))
            }
        }
        banner.setData(bannerList, mutableListOf())


        banner.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                Palette.from(BitmapFactory.decodeResource(resources, bannerList.get(position)))
                        .generate {
                            /*
                              Vibrant:有活力的；
                              Dark:暗淡的；
                              Muted:温和的；
                              Light:明亮的；
                              Dominant：主要的
                             */
                            it?.dominantSwatch?.let {
                                iv_bg.setBackgroundColor(it.rgb)
                                mImmersionBar.statusBarColorInt(it.rgb).init()
                                toolbar.backgroundColor = it.rgb
                            }
                        }
            }
        })
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, PaletteActivity::class.java))
        }
    }
}