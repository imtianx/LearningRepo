package cn.imtianx.mdsimple.status

import android.widget.ImageView
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseFragment
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.fragment_tab_one.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/8/27 5:00 PM
 */
class TabOneFragment : BaseFragment() {

    private val bannerList = listOf(R.mipmap.ic_banner_1, R.mipmap.ic_banner_2, R.mipmap.ic_banner_3)


    override fun getContentLayoutId(): Int {
        return R.layout.fragment_tab_one
    }


    override fun initWidget() {
        super.initWidget()
        banner.setAdapter { _, itemView, model, _ ->
            model?.let {
                (itemView as ImageView).setImageResource((it as Int))
            }
        }
        banner.setData(bannerList, mutableListOf())
        ImmersionBar.setTitleBar(activity, banner)
    }

    companion object {
        fun newInstance(): TabOneFragment = TabOneFragment()
    }

}
