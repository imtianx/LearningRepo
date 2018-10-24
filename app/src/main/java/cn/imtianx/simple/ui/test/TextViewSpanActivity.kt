package cn.imtianx.simple.ui.test

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import cn.imtianx.jetpacklearning.common.base.BaseActivity
import cn.imtianx.simple.R
import kotlinx.android.synthetic.main.activity_text_view_span.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/10/10 5:49 PM
 */
class TextViewSpanActivity : BaseActivity() {
    override fun getContentLayoutId(): Int {
        return R.layout.activity_text_view_span
    }

    override fun initWidget() {
        super.initWidget()

        btn_tv_1.setOnClickListener {
            val text = et_1.text.toString()
            if (text.isNotEmpty()) {
//                val ssb = SpannableStringBuilder(text)
//                ssb.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorPrimary)),
//                        0, 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
//                tv_zdpp_tips.text = ssb

                updateTipTextColor(color = resources.getColor(R.color.colorPrimary), text = text)
            }


        }

        btn_tv_2.setOnClickListener {

            //            val spannedString = SpannableString(tv_zdpp_tips.text)
//            spannedString.setSpan(ForegroundColorSpan(Color.RED), 0, 4,
//                    Spannable.SPAN_INCLUSIVE_INCLUSIVE)
//            tv_zdpp_tips.text = spannedString

//            updateTipTextColor(color = Color.RED)
            tv_zdpp_tips.setTextColor(Color.RED)
            tv_zdpp_tips.text = "hahahahhahahhahahahahahhahahah"


        }


    }

    private fun updateTipTextColor(end: Int = -1, color: Int, text: String = "-1") {
        var end = end
        var text = text
        if (text == "-1") {
            text = tv_zdpp_tips.text.toString()
        }
        val spannable = SpannableStringBuilder(text)
        if (end == -1) {
            end = spannable.length
        }
        spannable.setSpan(ForegroundColorSpan(color), 0, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        tv_zdpp_tips.text = spannable
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, TextViewSpanActivity::class.java))
        }
    }
}