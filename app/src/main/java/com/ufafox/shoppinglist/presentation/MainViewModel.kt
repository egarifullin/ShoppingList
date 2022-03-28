package com.ufafox.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ufafox.shoppinglist.data.ShopListRepositoryImpl
import com.ufafox.shoppinglist.domain.*

class MainViewModel:ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
    }

    fun deleteShopItem(item: ShopItem){
        deleteShopItemUseCase.deleteShopItem(item)
    }

    fun changeEnableState(item :ShopItem){
        val newItem = item.copy(enabled = !item.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}