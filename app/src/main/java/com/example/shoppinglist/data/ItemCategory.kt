package com.example.shoppinglist.data

import com.example.shoppinglist.R

enum class ItemCategory {
    FOOD, ELECTRONIC, BOOK;

    fun getIcon(): Int {
        return when (this) {
            FOOD -> R.drawable.food
            ELECTRONIC -> R.drawable.electronic
            BOOK -> R.drawable.book
        }
    }
}