package cn.imtianx.common.net.base

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.util.Log
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

    @SuppressLint("CheckResult")
    protected fun <T> performResponseData(observable: Observable<T>): MutableLiveData<RespResult<T>> {
        val liveData = MutableLiveData<RespResult<T>>()

        observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ t ->
                Log.e("tx", "success:$t")
                liveData.value = RespResult.success(t)
            }, { throwable ->
                throwable.printStackTrace()
                liveData.value = RespResult.failed(throwable.localizedMessage)
            })

        return liveData
    }
}