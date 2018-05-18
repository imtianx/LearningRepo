package cn.imtianx.simple.ui.databinding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.imtianx.simple.R
import kotlinx.android.synthetic.main.activity_data_binding.*

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)

        initData()
        billsAdapter = BillsAdapter(billsData)
        recycler.adapter = billsAdapter
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
        }
    }

}