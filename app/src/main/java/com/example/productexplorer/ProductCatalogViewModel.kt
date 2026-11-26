package com.example.productexplorer

import androidx.lifecycle.ViewModel

class ProductCatalogViewModel : ViewModel() {

    private val allProducts: List<ProductUi> = sampleProducts()

    val categories: List<String> = sampleCategories()
}
