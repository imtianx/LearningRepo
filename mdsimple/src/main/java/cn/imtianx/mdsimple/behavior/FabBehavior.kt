package cn.imtianx.mdsimple.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/6/14 10:35 PM
 */
class FabBehavior(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {
    private var isVisible = true

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout,
                                     child: FloatingActionButton, directTargetChild: View,
                                     target: View, axes: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
                        target, axes)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton,
                                target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed:
                                Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed,
                dyConsumed, dxUnconsumed, dyUnconsumed)

        if (isVisible && dyConsumed > 0) {
            isVisible = false
            // hide
            ViewCompat.animate(child).scaleX(0f).scaleY(0f).start()

        } else if (dyConsumed < 0) {
            isVisible = true
            //show
            ViewCompat.animate(child).scaleX(1f).scaleY(1f).start()
        }

    }
}