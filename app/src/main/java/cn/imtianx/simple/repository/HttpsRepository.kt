package cn.imtianx.simple.repository

import android.arch.lifecycle.MutableLiveData
import cn.imtianx.common.net.base.BaseRepository
import cn.imtianx.common.net.resp.RespResult
import cn.imtianx.simple.model.UserInfoBean

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/10/17 9:16 AM
 */
object HttpsRepository : BaseRepository() {

    fun login(name: String, pwd: String): MutableLiveData<RespResult<UserInfoBean>> {
        return performResponseData(apiService.login(name, pwd))
    }

    fun login2(name: String, pwd: String) = apiService.login(name, pwd)


}