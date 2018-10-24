package cn.imtianx.simple.repository

import android.arch.lifecycle.MutableLiveData
import cn.imtianx.common.net.base.BaseRepository
import cn.imtianx.common.net.resp.RespResult

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 2:31 PM
 */
object TestApiRepository : BaseRepository() {

    fun testTimeout(): MutableLiveData<RespResult<String>> {
        return performResponseData(apiService.testTimeout())
    }
}