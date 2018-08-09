package cn.imtianx.mdsimple.behavior

import android.animation.ArgbEvaluator
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import cn.imtianx.mdsimple.R

/**
 * <pre>
 *     @desc: 顶部搜索框 behavior  {@link R#string#search_behavior_title}
 * </pre>
 * @author 奚岩
 * @date 2018/8/8 3:50 PM
 */
class HeaderSearchBehavior(var context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>() {

    /**
     *  search init height
     */
    private var mSearchInitY = context.resources.getDimensionPixelOffset(R.dimen.header_search_init_y)

    /**
     * search collapsed height
     */
    private var mSearchCollapsedHeight = context.resources.getDimensionPixelOffset(R.dimen.header_search_collapsed_height)

    /**
     *  seearch margin left when collasped
     */
    private var mMargin = context.resources.getDimensionPixelOffset(R.dimen.header_search_margin)

    /**
     * argb to change search background color
     */
    private var argbEvaluator = ArgbEvaluator()

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        // return super.onLayoutChild(parent, child, layoutDirection)

        return if (dependency == null) {
            false
        } else {
            dependency.id == R.id.ll_search  // @{id = the view which using this behavior }
        }
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View?, dependency: View?): Boolean {
        // return super.onDependentViewChanged(parent, child, dependency)

        return if (dependency != null && child != null) {

            // set translationY
            val process = 1f - Math.abs(dependency.translationY / (dependency.height - mSearchCollapsedHeight))
            val translationY = (mSearchInitY - mSearchCollapsedHeight) * process
            child.translationY = translationY

            // set background color
            val startBgColor = context.resources.getColor(R.color.search_header_bg_start)
            val endBgColor = context.resources.getColor(R.color.search_header_bg_end)
            dependency.setBackgroundColor(argbEvaluator.evaluate(process, endBgColor, startBgColor) as Int)

            // set margin left and right
            val params = child.layoutParams as CoordinatorLayout.LayoutParams
            val margin = (mMargin * (3f - process)).toInt()
            Log.e("tx", "margin:   $margin")
            params.setMargins(margin, 0, margin, 0)
            child.layoutParams = params

            true
        } else {
            false
        }
    }
}