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

    var showOnlyInStock by mutableStateOf(false)
        private set

    fun onToggleStockFilter() {
        showOnlyInStock = !showOnlyInStock
    }

    var favoriteProductIds by mutableStateOf(listOf<Int>())
        private set

    fun onFavoriteClick(productId: Int) {
        favoriteProductIds = if (favoriteProductIds.contains(productId)) {
            favoriteProductIds - productId
        } else {
            favoriteProductIds + productId
        }
    }
}
