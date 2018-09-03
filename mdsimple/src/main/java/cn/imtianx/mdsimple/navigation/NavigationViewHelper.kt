package cn.imtianx.mdsimple.navigation

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log
import org.jetbrains.anko.forEachChild

/**
 * <pre>
 *     @desc: BottomNavigationView helper
 * </pre>
 * @author 奚岩
 * @date 2018/7/31 9:06 AM
 */
object NavigationViewHelper {

    /**
     *  disable the shiftingMode  of BottomNavigationView when the menu items more then three
     */
    @SuppressLint("RestrictedApi")
    fun disableShiftingMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView

        if (menuView.childCount > 3) {

            try {
                // BottomNavigationMenuView.java:Row270:  ShiftingMode = mMenu.size() > 3;
                val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
                shiftingMode.apply {
                    isAccessible = true
                    setBoolean(menuView, false)
                    isAccessible = false
                }

                menuView.forEachChild {
                    (it as BottomNavigationItemView).apply {
//                        setShiftingMode(false)
                        // reset check state to update it
                        setChecked(itemData.isChecked)
                    }
                }

            } catch (e: NoSuchFieldException) {
                Log.e("tx", "NavigationViewHelper: Unable to get shiftMode field", e)
            } catch (e: IllegalAccessException) {
                Log.e("tx", "NavigationViewHelper: Unable to change value of shiftMode", e)
            }
        }
    }

}