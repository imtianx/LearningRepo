package cn.imtianx.simple.raisepriority

import android.annotation.SuppressLint
import android.app.Service
import android.os.Handler
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.RemoteViews
import cn.imtianx.simple.R

/**
 * <pre>
 *     @desc: 提升进程优先级
 * </pre>
 * @author 奚岩
 * @date 2018/7/30 11:24 AM
 */
object RaisePriorityHack {

    private const val NOTIFICATION_ID = 1

    // android.app.ActivityThread$H   SCHEDULE_CRASH
    private var mScheduleCrashMsgWhat = 134

    private var mHasHookH = false

    fun raisePriority(service: Service) {

        hookH()

        val notification = NotificationCompat
                .Builder(service, "raise priority")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("content title")
                .setSubText("sub text")
                .setCustomContentView(RemoteViews(service.packageName, 1))
                .build()

        service.startForeground(NOTIFICATION_ID, notification)

    }

    @SuppressLint("PrivateApi")
    private fun hookH() {
        if (mHasHookH) {
            return
        }

        mHasHookH = true

        try {
            val atdHClass = Class.forName("""android.app.ActivityThread${'$'}H""")
            val scheduleCrashField = atdHClass.getDeclaredField("SCHEDULE_CRASH")
            mScheduleCrashMsgWhat = scheduleCrashField.get(null) as Int
            Log.e("tx", "mScheduleCrashMsgWhat : $mScheduleCrashMsgWhat     success")
        } catch (e: Exception) {
            Log.e("tx", "mScheduleCrashMsgWhat  faile")
            e.printStackTrace()
        }

        val handlerCallback = Handler.Callback { msg ->
            msg?.let {
                if (it.what == mScheduleCrashMsgWhat) {
                    return@Callback true
                }
            }
            false
        }

        try {

            val atdClass = Class.forName("android.app.ActivityThred")
            val mH = atdClass.getDeclaredField("nH")
            mH.isAccessible = true

            val curAtdMethod = atdClass.getDeclaredMethod("currentActivityThread")
            val atdInstance = curAtdMethod.invoke(null)

            val hInstance = mH.get(atdInstance) as Handler

            val callbackField = Handler::class.java.getDeclaredField("mCallback")
            callbackField.isAccessible = true
            callbackField.set(hInstance, handlerCallback)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}