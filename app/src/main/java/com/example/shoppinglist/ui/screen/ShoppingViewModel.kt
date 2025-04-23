package com.example.shoppinglist.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.ShoppingDAO
import com.example.shoppinglist.data.ShoppingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val dao: ShoppingDAO
) : ViewModel() {

    val shoppingItems: Flow<List<ShoppingItem>> = dao.getAllItems()

    fun addItem(item: ShoppingItem) = viewModelScope.launch {
        dao.insert(item)
    }

    fun updateItem(item: ShoppingItem) = viewModelScope.launch {
        dao.update(item)
    }

    fun deleteItem(item: ShoppingItem) = viewModelScope.launch {
        dao.delete(item)
    }

    fun clearAll() = viewModelScope.launch {
        dao.deleteAll()
    }

    fun getItemById(id: Int): Flow<ShoppingItem> {
        return dao.getItemById(id)
    }
}