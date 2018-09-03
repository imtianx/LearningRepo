package cn.imtianx.mdsimple.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

/**
 * <pre>
 *     @desc: Title behavior
 *     @see: https://www.jianshu.com/p/82d18b0d18f4
 * </pre>
 * @author 奚岩
 * @date 2018/8/6 5:11 PM
 */
class TitleBehavior constructor(context: Context? = null, attrs: AttributeSet? = null) :
        CoordinatorLayout.Behavior<View>(context, attrs) {

    private var deltaY = 0f

//    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
//        return dependency is NestedScrollView
//    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        if (deltaY == 0f) {
            deltaY = dependency.y - child.height
        }

        var dy = dependency.y - child.height
        dy = if (dy < 0f) {
            0f
        } else {
            dy
        }
        val y = -(dy / deltaY) * child.height
        child.translationY = y

        val alpha = 1 - (dy / deltaY)
        child.alpha = alpha

        return true
    }


}