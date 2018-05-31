package cn.imtianx.common.net.resp

/**
 * <pre>
 *     @desc: common result
 * </pre>
 * @author 奚岩
 * @date 2018/5/31 6:14 PM
 */
class RespResult<out T>(val code: Int = RESP_CODE_SUCCESS, val msg: String? = null, val data: T?) {

    fun isSuccess() = code == RESP_CODE_SUCCESS

    companion object {
        const val RESP_CODE_SUCCESS = 1
        const val RESP_CODE_ERROR = -1


        fun <T> success(data: T?): RespResult<T> {
            return success(data)
        }

        fun <T> failed(msg: String?, code: Int = RESP_CODE_ERROR): RespResult<T> {
            return RespResult(code, msg, null)
        }

    }

}

fun <T> RespResult<T>.applyActionWithNetworkData(successAction: (T) -> Unit,
                                                 failedAction: ((String, Int) -> Unit)?) {

    if (isSuccess()) {
        data?.let {
            successAction(it)
        }
    } else {
        if (failedAction != null) {
            failedAction(msg ?: "未知错误", code)
        } else {
            // todo
        }
    }

}

