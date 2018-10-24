package cn.imtianx.simple.api

import cn.imtianx.simple.model.UserInfoBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * <pre>
 *     @desc: api
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 2:20 PM
 */
interface ApiService {

    @GET("testTimeout")
    fun testTimeout(): Observable<String>

    @GET("article/list/0/json")
    fun testWan(): Observable<String>

    @GET("api/user/login")
    fun login(@Query("name") name: String, @Query("pwd") pwd: String): Observable<UserInfoBean>

}