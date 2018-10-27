package cn.imtianx.simple.ui

import android.app.Application
import cn.imtianx.common.utils.XConfig
import java.io.IOException
import java.io.InputStream

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/10/17 6:06 PM
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        XConfig.get().init(this)
    }

    fun getCertificates(): Array<InputStream>? {
        val cerFileName = "sercer.cer"
        try {
            return arrayOf(instance().assets.open(cerFileName))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }


    companion object {
        private var INSTANCE: App? = null
        fun instance() = INSTANCE!!
    }
}