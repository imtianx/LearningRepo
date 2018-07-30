package cn.imtianx.mdsimple.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * <pre>
 *     @desc:  extensions
 * </pre>
 * @author 奚岩
 * @date 2018/5/29 9:51 PM
 */
fun <T : ViewModel> AppCompatActivity.getViewModel(clazz: Class<T>): T {
    return ViewModelProviders.of(this).get(clazz)
}

fun <T : ViewModel> Fragment.getViewModel(clazz: Class<T>): T {
    return ViewModelProviders.of(this).get(clazz)
}

