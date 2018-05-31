package cn.imtianx.common.net

import cn.imtianx.common.net.converter.RespTypeAdapterFactory
import cn.imtianx.common.net.interceptor.RetryInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetryInterceptor())
                .build()

        val gson = GsonBuilder()
                .registerTypeAdapterFactory(RespTypeAdapterFactory())
                .create()

        return Retrofit.Builder()
                .baseUrl("http://192.168.7.16:8081/ksbapi")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .validateEagerly(true)
                .build()

    }


    companion object {
        fun get() = Inner.INSTANCE
    }

    private object Inner {
        val INSTANCE = HttpRequestClient()
    }
}