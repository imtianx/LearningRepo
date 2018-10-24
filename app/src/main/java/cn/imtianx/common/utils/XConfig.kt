package cn.imtianx.common.utils

import android.annotation.SuppressLint
import android.content.Context

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/10/18 9:18 PM
 */
class XConfig private constructor() {

    fun init(context: Context) {
        mContext = context
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        fun getContext() = mContext!!


        fun get() = Inner.INSTANCE

        private object Inner {
            @SuppressLint("StaticFieldLeak")
            val INSTANCE = XConfig()
        }
    }


}