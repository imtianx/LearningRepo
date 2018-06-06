package cn.imtianx.common.net.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * <pre>
 *     @desc: timeout retry interceptor
 * </pre>
 * @author 奚岩
 * @date 2018/5/30 01:42 PM
 */
class RetryInterceptor(var maxRetryNum: Int = 3) : Interceptor {

    private var retryNum = 0

    override fun intercept(chain: Interceptor.Chain?): Response {

        chain?.let {
            val request = it.request()
            var response = it.proceed(request)
            println("retry-----:$retryNum")
            while (!response.isSuccessful && retryNum < maxRetryNum) {
                retryNum++
                Log.e("tx", "retry:$retryNum")
                println("retry:$retryNum")
                response = it.proceed(request)
            }
            return response
        }
        throw IOException()
    }
}