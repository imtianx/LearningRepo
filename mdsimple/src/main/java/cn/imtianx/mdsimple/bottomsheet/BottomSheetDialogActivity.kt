package cn.imtianx.mdsimple.bottomsheet

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.dialog_bottom_sheet_camera.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * <pre>
 *     @desc: BottomSheetDialog
 * </pre>
 * @author 奚岩
 * @date 2018/8/2 5:29 PM
 */
class BottomSheetDialogActivity : BaseActivity() {

    private var bottomSheetDialog: BottomSheetDialog? = null

    override fun getContentLayoutId(): Int {
        return R.layout.activity_bottom_sheet
    }

    override fun initWidget() {
        super.initWidget()

        btn_bottom_sheet_list.setOnClickListener {
            showBottomSheetDialogList()
        }


        // bottom sheet
        val sheetBehavior = BottomSheetBehavior.from(sheetView)
        // 设置下滑时是否可以被隐藏，默认false,注意若设置为true,下滑关闭 shettView时其状态为 STATE_HIDDEN，否则为 STATE_COLLAPSED
        sheetBehavior.isHideable = true

        // 折叠后的高度
//        sheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO


        // set state change listener
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.e("tx", "newState:  $newState")
//                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                    sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//                }

            }

        })

        btn_bottom_sheet.setOnClickListener {

            // 1.
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                // 注意：使用该属性必须设置 isHideable = true
                sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }

            // 2.若设置了 isHideable = true 需要在 onStateChanged中 将 STATE_HIDDEN 设置为 STATE_COLLAPSED
//            if (sheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
//                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//            } else if (sheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
//                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//            }
        }

    }


    // 模拟列表
    private fun showBottomSheetDialogList() {
        dismissDialog()
        bottomSheetDialog = BottomSheetDialog(this)
        val dialogContentView = layoutInflater.inflate(R.layout.dialog_bottom_sheet_list, null)

        bottomSheetDialog?.apply {
            setContentView(dialogContentView)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.menu_bottom_sheet_camera, it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_bottom_sheet_camera) {
            showBottomSheetDialogCamera()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    // 显示选择 对话框
    private fun showBottomSheetDialogCamera() {

        dismissDialog()

        bottomSheetDialog = BottomSheetDialog(this)

        val dialogContentView = layoutInflater.inflate(R.layout.dialog_bottom_sheet_camera, null)

        dialogContentView.apply {
            // cliec event
            tv_camera.onClick {
                Toast.makeText(this@BottomSheetDialogActivity,
                        "camera", Toast.LENGTH_SHORT).show()
                dismissDialog()
            }

            tv_album.onClick {
                Toast.makeText(this@BottomSheetDialogActivity,
                        "album", Toast.LENGTH_SHORT).show()
                dismissDialog()
            }

            tv_cancel.onClick {
                Toast.makeText(this@BottomSheetDialogActivity,
                        "cancel", Toast.LENGTH_SHORT).show()
                dismissDialog()
            }
        }


        bottomSheetDialog?.apply {

            setContentView(dialogContentView)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            show()


            // behavior
            val sheetView = delegate
                    .findViewById<View>(android.support.design.R.id.design_bottom_sheet)

            sheetView?.let {

                it.setBackgroundColor(Color.TRANSPARENT)

                val behavior = BottomSheetBehavior.from(it)

                behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            dismissDialog()
                            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                })
            }
        }
    }


    private fun dismissDialog() {
        bottomSheetDialog?.let {
            it.dismiss()
            bottomSheetDialog = null
        }
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, BottomSheetDialogActivity::class.java))
        }
    }
}