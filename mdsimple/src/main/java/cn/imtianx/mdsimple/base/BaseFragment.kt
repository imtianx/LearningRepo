package cn.imtianx.mdsimple.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * <pre>
 *     @desc: base fragment
 * </pre>
 * @author 奚岩
 * @date 2018/5/29 9:33 PM
 */
abstract class BaseFragment : Fragment() {

    protected var mActivity: BaseActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(getContentLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWidget()

        initData()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context?.let {
            it as BaseActivity
        }
    }

    protected open fun initData() {}

    protected open fun initWidget() {}

    abstract fun getContentLayoutId(): Int

}