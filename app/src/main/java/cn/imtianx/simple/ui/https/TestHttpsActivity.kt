package cn.imtianx.simple.ui.https

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import cn.imtianx.common.net.NetworkUtils
import cn.imtianx.common.net.resp.applyActionWithNetworkData
import cn.imtianx.jetpacklearning.common.base.BaseActivity
import cn.imtianx.jetpacklearning.common.extentions.getViewModel
import cn.imtianx.simple.R
import kotlinx.android.synthetic.main.activity_https.*
import org.jetbrains.anko.toast

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/10/16 8:31 PM
 */
class TestHttpsActivity : BaseActivity() {

    private val viewModel: HttpsViewModel by lazy {
        getViewModel(HttpsViewModel::class.java)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_https
    }

    override fun initWidget() {

        // login
        btn_login.setOnClickListener {
            if (et_name.text.isEmpty()) {
                toast("请输入用户名！")
                return@setOnClickListener
            }

            if (et_pwd.text.isEmpty()) {
                toast("请输入密码！")
                return@setOnClickListener
            }
            viewModel.login(et_name.text.toString(), et_pwd.text.toString())
        }

        // check vpn state
        btn_check_vpn.setOnClickListener {
            toast("vpn状态：${NetworkUtils.isVpnEnabled()}")
        }

        // check wifi proxy
        btn_check_wifi_proxy.setOnClickListener {
            toast("wifi 代理状态：${NetworkUtils.isWifiProxy()}")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        viewModel.loginResult.observe(this, Observer { result ->
            result?.applyActionWithNetworkData(
                {
                    tv_msg.text = "返回结果：$it"
                }, { msg, _ ->
                    tv_msg.text = "错误信息：$msg"
                }
            )
        })
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, TestHttpsActivity::class.java))
        }
    }
}