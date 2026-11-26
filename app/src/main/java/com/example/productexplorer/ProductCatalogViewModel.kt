package com.example.productexplorer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProductCatalogViewModel : ViewModel() {

    private val allProducts: List<ProductUi> = sampleProducts()

    val categories: List<String> = sampleCategories()

    var searchQuery by mutableStateOf("")
        private set

    fun onSearchQueryChange(newValue: String) {
        searchQuery = newValue
    }
}
