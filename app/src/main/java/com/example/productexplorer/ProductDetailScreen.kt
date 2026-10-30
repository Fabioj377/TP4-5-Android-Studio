package com.example.productexplorer

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductDetailScreen(
    product: ProductUi,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = spacedBy(16.dp)
    ) {
        ProductHeader(product = product)
    }
}

@Composable
fun ProductHeader(
    product: ProductUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(4.dp)
    ) {
        Text(
            text = product.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = product.brand,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = product.category,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}