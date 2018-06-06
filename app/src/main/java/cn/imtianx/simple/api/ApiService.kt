package cn.imtianx.simple.api

import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * <pre>
 *     @desc: api
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 2:20 PM
 */
interface ApiService {

    @GET("/testTimeout")
    fun testTimeout(): Observable<String>

    @GET("/article/list/0/json")
    fun testWan(): Observable<String>

}