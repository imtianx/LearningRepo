package cn.imtianx.common.net

import android.annotation.SuppressLint
import java.io.IOException
import java.io.InputStream
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*


/**
 * <pre>
 * @desc: https util
 * </pre>
 *
 * @author 奚岩
 * @date 2018/10/15 8:22 PM
 */
object HttpsUtils {

    val unSafeHostnameVerifier: HostnameVerifier
        get() = UnSafeHostnameVerifier()

    class SSLParams(
        var sSLSocketFactory: SSLSocketFactory,
        var trustManager: X509TrustManager
    )

    @JvmOverloads
    fun getSslSocketFactory(
        certificates: Array<InputStream?>,
        bksFile: InputStream? = null,
        password: String? = null
    ): SSLParams? {
        try {
            val trustManagers =
                prepareTrustManager(*certificates)
            val keyManagers =
                prepareKeyManager(bksFile, password)
            val sslContext = SSLContext.getInstance("TLS")
            val trustManager: X509TrustManager
            trustManager = if (trustManagers != null) {
                MyTrustManager(
                    chooseTrustManager(
                        trustManagers
                    )
                )
            } else {
                UnSafeTrustManager()
            }
            sslContext.init(keyManagers, arrayOf<TrustManager>(trustManager), null)
            return SSLParams(sslContext.socketFactory, trustManager)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun prepareTrustManager(vararg certificates: InputStream?): Array<TrustManager>? {
        if (certificates.isEmpty()) return null
        try {
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null)

            certificates.forEachIndexed { index, certificate ->
                val certificateAlias = index.toString()
                keyStore.setCertificateEntry(
                    certificateAlias,
                    certificateFactory.generateCertificate(certificate)
                )
                try {
                    certificate?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            val trustManagerFactory: TrustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())

            trustManagerFactory.init(keyStore)

            return trustManagerFactory.trustManagers
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null

    }

    private fun prepareKeyManager(bksFile: InputStream?, password: String?): Array<KeyManager>? {
        try {
            if (bksFile == null || password == null) return null

            val clientKeyStore = KeyStore.getInstance("BKS")
            clientKeyStore.load(bksFile, password.toCharArray())
            val keyManagerFactory = KeyManagerFactory
                .getInstance(KeyManagerFactory.getDefaultAlgorithm())
            keyManagerFactory.init(clientKeyStore, password.toCharArray())

            return keyManagerFactory.keyManagers
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    private fun chooseTrustManager(trustManagers: Array<TrustManager>): X509TrustManager? {
        for (trustManager in trustManagers) {
            if (trustManager is X509TrustManager) {
                return trustManager
            }
        }
        return null
    }

    private class MyTrustManager @Throws(NoSuchAlgorithmException::class, KeyStoreException::class)
    constructor(private val localTrustManager: X509TrustManager?) : X509TrustManager {
        private val defaultTrustManager: X509TrustManager?

        init {
            val tmf = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm())
            tmf.init(null as KeyStore?)
            defaultTrustManager =
                    chooseTrustManager(tmf.trustManagers)
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            try {
                defaultTrustManager?.checkServerTrusted(chain, authType)
            } catch (ce: CertificateException) {
                localTrustManager?.checkServerTrusted(chain, authType)
            }
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }

    private class UnSafeTrustManager : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }

    private class UnSafeHostnameVerifier : HostnameVerifier {
        @SuppressLint("BadHostnameVerifier")
        override fun verify(hostname: String, session: SSLSession): Boolean {
            return true
        }
    }

}