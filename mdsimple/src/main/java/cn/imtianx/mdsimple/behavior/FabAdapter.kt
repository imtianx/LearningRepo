package cn.imtianx.mdsimple.behavior

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.imtianx.mdsimple.R
import cn.imtianx.mdsimple.databinding.ItemFabBinding

/**
 * <pre>
 *     @desc:
 * </pre>
 * @author 奚岩
 * @date 2018/6/14 9:23 PM
 */
class Test1Adapter(var datas: MutableList<String>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil
                .inflate<ItemFabBinding>(LayoutInflater.from(parent.context),
                        R.layout.item_fab, parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<ItemFabBinding>(holder.itemView)
        binding?.item = datas[position]
        binding?.executePendingBindings()
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)