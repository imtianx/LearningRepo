package cn.imtianx.simple.repository.base

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 2:33 PM
 */
open class BaseRepository {

    protected fun <T> performResponData(observable: Observable<T>): MutableLiveData<T> {
        var liveData = MutableLiveData<T>()

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ t ->
                    liveData.value = t
                    Response
                }, { throwable ->
                    liveData.value = throwable.localizedMessage
                })

        return liveData
    }
}