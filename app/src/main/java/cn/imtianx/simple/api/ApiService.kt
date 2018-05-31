package cn.imtianx.simple.api

import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * <pre>
 *     @desc: api
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 2:20 PM
 */
interface ApiService {

    @POST("/testTimeout")
    @FormUrlEncoded
    fun testTimeout(): Observable<String>

}