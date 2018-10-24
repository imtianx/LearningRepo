package cn.imtianx.simple

import cn.imtianx.common.net.HttpRequestClient
import cn.imtianx.simple.api.ApiService
import cn.imtianx.simple.repository.HttpsRepository
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

    @Test
    fun testTemp() {
        println("""android.app.ActivityThread${"$"}H""")
    }

    @Test
    fun testLogin(){
        println(HttpsRepository.login2("imtianx","123456").blockingSingle())
    }

}
