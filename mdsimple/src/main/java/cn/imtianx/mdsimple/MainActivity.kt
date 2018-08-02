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
        /**
         * 1.若设置居中标题，需手动设置toolbar 的titile 为空，并且如果使用了 setSupportActionBar方法，需在其之前设置
         * 2.若该activity 为启动类，不能在 manifests 中对应的 activity 设置 label 属性，若设置为空或者其他值，会改变app 桌面显示名称
         */
        toolbar.title = ""
    }

    override fun initWidget() {
        super.initWidget()
        binding.viewModel = viewModel
        // 设置居中标题
        tvTitle.text = resources.getString(R.string.app_name)
    }
}
