package cn.imtianx.simple.ui.databinding

import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableDouble
import android.databinding.ObservableField
import android.util.Log

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/17 下午4:57
 */
data class BillsData(
        var date: String,
        var totalPrice: String,
        var billSingles: List<BillSingle>) {


    var billsChecked = ObservableBoolean(false)

    var billsAmount = ObservableDouble(0.0)

    var testCbChecked = ObservableField<String>()


    fun refreshAmount() {
        val totalAmount = billSingles.filter { it.billSingleChecked.get() }
                .sumByDouble { it.totalPrice.toDouble() }

        billsAmount.set(totalAmount)
    }

    var checkCount = 0


    fun setupObservableField(onAmountChangeCallBack: Observable.OnPropertyChangedCallback) {
        billsChecked = ObservableBoolean(false)
        billSingles.forEach {
            it.setupObservableField()

            it.billSingleChecked.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (sender is ObservableBoolean) {
                        val billSingleChecked = sender.get()
                        if (billSingleChecked) {
                            checkCount++
                        } else {
                            checkCount--
                        }
                        Log.e("tx", "checkCount      : $checkCount")
                        billsChecked.set(checkCount == billSingles.size)
                        refreshAmount()
                    }
                }

            })
        }
        billsAmount = ObservableDouble(0.0)
        billsAmount.addOnPropertyChangedCallback(onAmountChangeCallBack)

    }

}

data class BillSingle(
        var date: String,
        var totalPrice: String
) {
    var billSingleChecked = ObservableBoolean(false)

    fun setupObservableField(){
        billSingleChecked = ObservableBoolean(false)
    }
}