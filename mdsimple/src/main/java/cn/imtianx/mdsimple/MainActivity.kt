package cn.imtianx.mdsimple

import cn.imtianx.mdsimple.base.BaseDataBindingActivity
import cn.imtianx.mdsimple.base.getViewModel
import cn.imtianx.mdsimple.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.layout_toolbar_center_title.*

/**
 * <pre>
 *     @desc: main
 * </pre>
 * @author 奚岩
 * @date 2018/6/14 11:20 PM
 */
class MainActivity : BaseDataBindingActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by lazy {
        getViewModel(MainViewModel::class.java)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initWindows() {
        super.initWindows()
        isShowBack = false
    }

    override fun initWidget() {
        super.initWidget()
        binding.viewModel = viewModel
        tvTitle.text = resources.getString(R.string.app_name)
    }
}
