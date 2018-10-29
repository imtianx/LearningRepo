package cn.imtianx.common.net

import cn.imtianx.common.net.converter.RespTypeAdapterFactory
import cn.imtianx.common.net.interceptor.RetryInterceptor
import cn.imtianx.simple.BuildConfig
import cn.imtianx.simple.ui.App
import com.google.gson.GsonBuilder
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
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(RetryInterceptor())
            .apply {
                HttpsUtils.getSslSocketFactory(App.instance().getCertificates())?.let {
                    sslSocketFactory(it.sSLSocketFactory, it.trustManager)
                }
                if (BuildConfig.DEBUG) {
                    hostnameVerifier(HttpsUtils.unSafeHostnameVerifier)
                }
            }
            .build()


        val gson = GsonBuilder()
            .registerTypeAdapterFactory(RespTypeAdapterFactory())
            .create()

        return Retrofit.Builder()
//            .baseUrl("https://192.168.0.236/")
            .baseUrl("https://testhttps.com/")
//            .baseUrl("https://mvvm.tech/")
//            .baseUrl("https://192.168.2.104/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    private fun getSSLParams(): HttpsUtils.SSLParams? {
        return HttpsUtils.getSslSocketFactory(App.instance().getCertificates())
    }


    companion object {
        fun get() = Inner.INSTANCE
    }

    private object Inner {
        val INSTANCE = HttpRequestClient()
    }
}