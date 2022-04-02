package com.ufafox.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ufafox.shoppinglist.R
import com.ufafox.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType){
            DISABLED_TYPE -> R.layout.item_shop_disabled
            ENABLED_TYPE -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown viewType  $viewType")
        }
        val view = LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.enabled) {
            ENABLED_TYPE
        } else {
            DISABLED_TYPE
        }
    }


    companion object{
        const val ENABLED_TYPE = 1
        const val DISABLED_TYPE = 0

        const val MAX_POOL_SIZE = 30
    }
}