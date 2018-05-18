package cn.imtianx.simple.ui.databinding

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import cn.imtianx.simple.BR
import cn.imtianx.simple.R

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/17 下午9:02
 */
class BillsAdapter(var data: List<BillsData>) : RecyclerView.Adapter<BillsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context),
                        R.layout.item_bills, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.findViewById<CheckBox>(R.id.cb_all).apply {
            tag = position
            setOnClickListener {
                var checked = false
                if (it is CheckBox) {
                    checked = it.isChecked
                }
                data[it?.tag as Int].let {
                    for (bill in it.billSingles) {
                        bill.billSingleChecked.set(checked)
                    }
                }
            }
        }

        holder.bindingData(data[position])

        val billSingleAdapter = BillSingleAdapter(data[position].billSingles)
        holder.itemView.findViewById<RecyclerView>(R.id.item_recycler).apply {
            adapter = billSingleAdapter
            isNestedScrollingEnabled = false
        }
    }

    class ViewHolder(var viewDatabinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDatabinding.root) {

        fun bindingData(itemData: BillsData) {
            viewDatabinding.setVariable(BR.billsData, itemData)
        }
    }
}

