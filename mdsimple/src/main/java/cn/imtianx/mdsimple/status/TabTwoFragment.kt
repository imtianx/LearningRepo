package cn.imtianx.mdsimple.status

import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseFragment
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.fragment_tab_two.*
import kotlinx.android.synthetic.main.layout_toolbar_center_title.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/8/27 5:00 PM
 */
class TabTwoFragment : BaseFragment() {

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_tab_two
    }

    override fun initWidget() {
        super.initWidget()
        tvTitle.text = "购物车"
        ImmersionBar.setTitleBar(activity,toolbar)
    }


    companion object {
        fun newInstance(): TabTwoFragment = TabTwoFragment()
    }
}