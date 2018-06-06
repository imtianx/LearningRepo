package cn.imtianx.simple

import cn.imtianx.common.net.HttpRequestClient
import cn.imtianx.simple.api.ApiService
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testTimeout() {
        val data = HttpRequestClient.get().getRetrofit().create(ApiService::class.java).testTimeout().blockingSingle()
        println(Gson().toJson(data))
    }

}
