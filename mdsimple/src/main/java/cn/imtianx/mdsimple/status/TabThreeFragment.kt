package cn.imtianx.mdsimple.status

import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseFragment
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.fragment_tab_three.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/8/27 5:00 PM
 */
class TabThreeFragment : BaseFragment() {

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_tab_three
    }

    override fun initWidget() {
        super.initWidget()
        ImmersionBar.setTitleBar(activity, iv_bg_tab_3)
    }

    companion object {
        fun newInstance(): TabThreeFragment = TabThreeFragment()
    }

}