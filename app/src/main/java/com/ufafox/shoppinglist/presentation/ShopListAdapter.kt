package com.ufafox.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ufafox.shoppinglist.R
import com.ufafox.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {



    var shopList = listOf<ShopItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val layout = when (viewType){
            DISABLED_TYPE -> R.layout.item_shop_disabled
            ENABLED_TYPE -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown viewType  $viewType")
        }
        val view = LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener { true }
    }

    override fun getItemCount(): Int {
        return shopList.size;
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = shopList[position]
        return if (shopItem.enabled) {
            ENABLED_TYPE
        } else {
            DISABLED_TYPE
        }
    }

    class ShopListViewHolder(val view : View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    companion object{
        const val ENABLED_TYPE = 1
        const val DISABLED_TYPE = 0

        const val MAX_POOL_SIZE = 30
    }
}