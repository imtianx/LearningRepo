package cn.imtianx.common.base

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cn.imtianx.common.net.resp.RespResult

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 7:03 PM
 */
open class BaseViewModel : ViewModel() {

    protected fun <T> copyLiveData(srcLiveData: MutableLiveData<RespResult<T>>,
                                   destLiveData: MediatorLiveData<RespResult<T>>,
                                   block: (() -> Unit)? = null) {

        destLiveData.addSource(srcLiveData) {
            destLiveData.removeSource(srcLiveData)
            destLiveData.value = it
            block?.invoke()
        }
    }
}