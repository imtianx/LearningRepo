package cn.imtianx.simple.ui.https

import android.arch.lifecycle.MediatorLiveData
import cn.imtianx.common.base.BaseViewModel
import cn.imtianx.common.net.resp.RespResult
import cn.imtianx.simple.model.UserInfoBean
import cn.imtianx.simple.repository.HttpsRepository

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/10/17 9:28 AM
 */
class HttpsViewModel : BaseViewModel() {
    var loginResult = MediatorLiveData<RespResult<UserInfoBean>>()

    fun login(name: String, pwd: String) =
        copyLiveData(HttpsRepository.login(name, pwd), loginResult)
}