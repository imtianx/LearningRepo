package cn.imtianx.mdsimple.behavior

import android.content.Context
import android.os.Handler
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.widget.OverScroller
import cn.imtianx.mdsimple.R
import java.lang.ref.WeakReference

/**
 * <pre>
 *     @desc: 内容滚动 behavior
 * </pre>
 * @author 奚岩
 * @date 2018/8/8 3:51 PM
 */
class ContentScrollBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>() {

    private val mOverScroller = OverScroller(context)

    private val mHandler = Handler()

    private var isExpend = false

    private var isScrolling = false


    /**
     * search collapsed height
     */
    private var mSearchCollapsedHeight = context.resources.getDimensionPixelOffset(R.dimen.header_search_collapsed_height)

    private lateinit var mDependencyView: WeakReference<View>

//    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
//        return if (dependency != null && dependency.id == R.id.ll_search) {
//            mDependencyView = WeakReference(dependency)
//            true
//        } else {
//            false
//        }
//    }

//    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View, dependency: View): Boolean {
//
//        val resource = mDependencyView.get()!!.resources
//        val progress = 1f - Math.abs(dependency.rotationY / (dependency.height -
//                resource.getDimensionPixelOffset(R.dimen.header_offset)))
//        child.translationY = dependency.height + dependency.translationY
//
//        val scale = 1f + 0.4f * (1f - progress)
//        dependency.scaleX = scale
//        dependency.scaleY = scale
//        dependency.alpha = progress
//        return true
//
//    }

    override fun onLayoutChild(parent: CoordinatorLayout, child: View, layoutDirection: Int): Boolean {
        val params = child.layoutParams as CoordinatorLayout.LayoutParams
        if (params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(0, 0, parent.width, parent.height - mSearchCollapsedHeight)
        }
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {

        return axes == ViewCompat.SCROLL_AXIS_VERTICAL && type == ViewCompat.TYPE_TOUCH
    }


    override fun onNestedScrollAccepted(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int) {
        isScrolling = false
        mOverScroller.abortAnimation()
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
    }


    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {

        if (dy < 0) {
            return
        }
        val dependencyView = mDependencyView.get()!!
        val newTranslationY = dependencyView.translationY - dy
        val minHeaderTranslation = -(dependencyView.height - mSearchCollapsedHeight)
        if (newTranslationY > minHeaderTranslation) {
            dependencyView.translationY = newTranslationY
            consumed[1] = dy
        }
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        if (dyUnconsumed > 0) {
            return
        }
        val dependencyView = mDependencyView.get()!!
        val newTranslationY = dependencyView.translationY - dyUnconsumed
        val maxHeaderTranslation = 0f
        if (newTranslationY < maxHeaderTranslation) {
            dependencyView.translationY = newTranslationY
        }
    }

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return onUserStopDragging(velocityY)
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, type: Int) {

        if (!isScrolling) {
            onUserStopDragging(800f)
        }
    }

    private fun onUserStopDragging(velocityY: Float): Boolean {

        val dependencyView = mDependencyView.get()!!
        val translationY = dependencyView.translationY
        val minHeaderTranslation = -(dependencyView.height - mSearchCollapsedHeight).toFloat()

        if (translationY == 0f || translationY == minHeaderTranslation) {
            return false
        }

        var tempVelocityY: Float = velocityY
        val targetFlag = if (Math.abs(velocityY) <= 800) {
            tempVelocityY = 800f
            Math.abs(translationY) >= Math.abs(translationY - minHeaderTranslation)
        } else {
            velocityY > 0
        }

        val targetTranslationY = if (targetFlag) {
            minHeaderTranslation
        } else {
            0f
        }

        mOverScroller.startScroll(0, translationY.toInt(),
                0, (targetTranslationY - translationY).toInt(),
                (1000000 / Math.abs(tempVelocityY)).toInt())

        mHandler.post(flingRunnable)
        isScrolling = true

        return true
    }

    private val flingRunnable = object : Runnable {
        override fun run() {
            if (mOverScroller.computeScrollOffset()) {
                mDependencyView.get()!!.translationY = mOverScroller.currY.toFloat()
                mHandler.post(this)
            } else {
                isExpend = (mDependencyView.get()!!.translationY != 0f)
                isScrolling = false
            }
        }

    }
}