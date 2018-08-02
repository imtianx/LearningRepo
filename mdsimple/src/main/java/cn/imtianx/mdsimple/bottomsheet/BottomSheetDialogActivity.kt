package cn.imtianx.mdsimple.bottomsheet

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.base.BaseActivity
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