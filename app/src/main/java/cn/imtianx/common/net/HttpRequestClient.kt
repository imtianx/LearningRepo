package cn.imtianx.common.net

import cn.imtianx.common.net.converter.RespTypeAdapterFactory
import cn.imtianx.common.net.interceptor.RetryInterceptor
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * <pre>
 *     @desc: retrofit client
 * </pre>
 * @author 奚岩
 * @date 2018/5/30 11:45 PM
 */
class HttpRequestClient private constructor() {

    fun getRetrofit(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetryInterceptor())
                .build()

        val gson = GsonBuilder()
                .registerTypeAdapterFactory(RespTypeAdapterFactory())
                .create()

        return Retrofit.Builder()
                .baseUrl("http://192.168.2.107:8080")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }


    companion object {
        fun get() = Inner.INSTANCE
    }

    private object Inner {
        val INSTANCE = HttpRequestClient()
    }
}