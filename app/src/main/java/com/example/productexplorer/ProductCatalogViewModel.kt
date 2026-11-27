package com.example.productexplorer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ProductCatalogUiState(
    val products: List<ProductUi> = emptyList(),
    val categories: List<String> = emptyList(),
    val searchQuery: String = "",
    val showOnlyInStock: Boolean = false,
    val favoriteProductIds: List<Int> = emptyList()
)

class ProductCatalogViewModel : ViewModel() {

    private val allProducts: List<ProductUi> = sampleProducts()

    val categories: List<String> = sampleCategories()

    var searchQuery by mutableStateOf("")
        private set

    var showOnlyInStock by mutableStateOf(false)
        private set

    var favoriteProductIds by mutableStateOf(listOf<Int>())
        private set

    val filteredProducts: List<ProductUi>
        get() = allProducts.filter { product ->
            val matchesSearch =
                product.title.contains(searchQuery, ignoreCase = true) ||
                        product.brand.contains(searchQuery, ignoreCase = true) ||
                        product.category.contains(searchQuery, ignoreCase = true)

            val matchesStock =
                !showOnlyInStock || product.stock > 0

            matchesSearch && matchesStock
        }

    fun onSearchQueryChange(newValue: String) {
        searchQuery = newValue
    }

    fun onToggleStockFilter() {
        showOnlyInStock = !showOnlyInStock
    }

    fun onFavoriteClick(productId: Int) {
        favoriteProductIds = if (favoriteProductIds.contains(productId)) {
            favoriteProductIds - productId
        } else {
            favoriteProductIds + productId
        }
    }
}
