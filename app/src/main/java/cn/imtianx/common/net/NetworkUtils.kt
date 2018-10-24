package cn.imtianx.common.net

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.support.annotation.RequiresPermission
import android.util.Log
import cn.imtianx.common.utils.XConfig
import java.net.NetworkInterface

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/10/18 8:39 PM
 */
object NetworkUtils {

    @SuppressLint("WifiManagerLeak")
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    fun isWifiEnabled(): Boolean {
        val wifiManager =
            XConfig.getContext().getSystemService(Context.WIFI_SERVICE) as WifiManager?
        return wifiManager?.isWifiEnabled ?: false
    }

    fun isWifiProxy(): Boolean {

        if (!isWifiEnabled()) return false

        val isICSOrLater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
        val proxyAddress: String
        val proxyPort: Int

        if (isICSOrLater) {
            proxyAddress = System.getProperty("http.proxyHost") ?:
                    System.getProperty("https.proxyHost") ?: ""
            val tempPort =
                System.getProperty("http.proxyPort") ?: System.getProperty("https.proxyPort")
                ?: "-1"
            proxyPort = tempPort.toInt()
        } else {
            proxyAddress = android.net.Proxy.getHost(XConfig.getContext())
            proxyPort = android.net.Proxy.getPort(XConfig.getContext())
        }
        Log.e("tx", "proxyAddress:$proxyAddress,\tproxyPort:$proxyPort")
        return proxyAddress.isNotEmpty() && proxyPort != -1
    }

    fun isVpnEnabled(): Boolean {
        try {
            val nis = NetworkInterface.getNetworkInterfaces()
            for (ni in nis.toList()) {
                if (!ni.isUp || ni.interfaceAddresses.size == 0) continue

                if (ni.name in listOf("tun0", "ppp0")) return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}