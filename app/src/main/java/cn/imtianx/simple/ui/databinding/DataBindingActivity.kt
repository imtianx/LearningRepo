package cn.imtianx.simple.ui.databinding

import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import cn.imtianx.simple.BR
import cn.imtianx.simple.R
import kotlinx.android.synthetic.main.activity_data_binding.*
import java.util.*

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/17 下午5:12
 */
class DataBindingActivity : AppCompatActivity() {

    private lateinit var billsAdapter: BillsAdapter

    private var billsData = ArrayList<BillsData>()

    private lateinit var binding: ViewDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ViewDataBinding>(this,
                R.layout.activity_data_binding)

        initData()
        billsAdapter = BillsAdapter(billsData)
        recycler.adapter = billsAdapter

        binding.setVariable(BR.mainBillsData, billsData[0])

    }

    private fun initData() {
        for (i in 0..3) {

            val billSinges = ArrayList<BillSingle>()

            for (j in 0..2) {
                val billSingle = BillSingle("16-00${j + 1}", "100")
                billSinges.add(billSingle)
            }

            val billData = BillsData("2018-05-16-${(i + 1) * 100}", "300", billSinges)
            billsData.add(billData)
            billData.refreshAmount()
            billData.setupObservableField(onAmountChangeCallBack)
        }
    }

    private val onAmountChangeCallBack = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            refreshAmount()
        }

    }

    private fun refreshAmount() {
        var totalSelectPrice = 0.0
        billsData.asSequence()
                .filter {
                    it.checkCount > 0
                }.forEach {
                    totalSelectPrice += it.billsAmount.get()
                }

        Log.e("tx", "refreshAmount      ")

        binding.setVariable(BR.mainTotalAmount, totalSelectPrice.toString())
    }

}