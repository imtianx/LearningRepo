package cn.imtianx.simple.ui.databinding

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.imtianx.simple.BR
import cn.imtianx.simple.R

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/5/17 下午11:29
 */
class BillSingleAdapter(var data: List<BillSingle>) : RecyclerView.Adapter<BillSingleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context),
                        R.layout.item_bills_single, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingData(data[position])
    }


    class ViewHolder(var viewDatabinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDatabinding.root) {

        fun bindingData(itemData: BillSingle) {
            viewDatabinding.setVariable(BR.billSingle, itemData)
        }
    }
}