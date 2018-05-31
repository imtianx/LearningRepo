package cn.imtianx.common.net.base

import android.arch.lifecycle.MutableLiveData
import cn.imtianx.common.net.HttpRequestClient
import cn.imtianx.common.net.resp.RespResult
import cn.imtianx.simple.api.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * <pre>
 *     @desc: base respository
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 2:33 PM
 */
open class BaseRepository {

    protected val apiService: ApiService by lazy {
        HttpRequestClient.get().getRetrofit().create(ApiService::class.java)
    }

    protected fun <T> performResponData(observable: Observable<T>): MutableLiveData<RespResult<T>> {
        var liveData = MutableLiveData<RespResult<T>>()

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ t ->
                    liveData.value = RespResult.success(t)
                }, { throwable ->
                    liveData.value = RespResult.failed(throwable.localizedMessage)
                })

        return liveData
    }
}