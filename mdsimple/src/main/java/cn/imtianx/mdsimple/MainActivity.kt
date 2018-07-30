package cn.imtianx.mdsimple

import cn.imtianx.MainViewModel
import cn.imtianx.mdsimple.base.BaseDataBindingActivity
import cn.imtianx.mdsimple.base.getViewModel
import cn.imtianx.mdsimple.databinding.ActivityMainBinding

/**
 * <pre>
 *     @desc: main
 * </pre>
 * @author 奚岩
 * @date 2018/6/14 11:20 PM
 */
class MainActivity : BaseDataBindingActivity<ActivityMainBinding>() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    private val viewModel: MainViewModel by lazy {
        getViewModel(MainViewModel::class.java)
    }

    override fun initWidget() {
        super.initWidget()
        binding.viewModel = viewModel
    }
}
