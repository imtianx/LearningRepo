package cn.imtianx.simple.ui.viewmodel

import android.arch.lifecycle.MediatorLiveData
import cn.imtianx.common.base.BaseViewModel
import cn.imtianx.common.net.resp.RespResult
import cn.imtianx.simple.repository.TestApiRepository

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 6:59 PM
 */
class MainViewModel : BaseViewModel() {

    val timeOutResult = MediatorLiveData<RespResult<String>>()

    fun fetchTimeout() = copyLiveData(TestApiRepository.testTimeout(), timeOutResult)
}