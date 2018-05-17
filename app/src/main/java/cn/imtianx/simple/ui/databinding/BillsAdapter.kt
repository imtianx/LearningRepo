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
 * @date 2018/5/17 下午9:02
 */
class BillAdapter(var datas: ArrayList<BillDataResp>) : RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context),
                        R.layout.item_bills, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingData(datas.get(position))

    }


    class ViewHolder(var viewDatabinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDatabinding.root) {

        fun bindingData(itemData: BillDataResp) {
            viewDatabinding.setVariable(BR.adapter, itemData)
        }
    }
}

