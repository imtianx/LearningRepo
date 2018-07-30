package cn.imtianx.simple.raisepriority

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/7/30 11:23 AM
 */
class HighPriorityService : Service() {

    override fun onBind(intent: Intent?): IBinder {
        throw UnsupportedOperationException("not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        RaisePriorityHack.raisePriority(this)
    }

}