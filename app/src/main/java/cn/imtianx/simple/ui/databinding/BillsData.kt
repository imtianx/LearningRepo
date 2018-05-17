package cn.imtianx.simple.ui.databinding

import android.databinding.ObservableField

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/17 下午4:57
 */
data class BillDataResp(var neme: String, var age: Int) {

    var desc = ObservableField<String>()
}