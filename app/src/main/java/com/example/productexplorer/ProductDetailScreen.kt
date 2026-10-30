package com.example.productexplorer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productexplorer.ui.theme.ProductExplorerTheme

@Composable
fun ProductDetailScreen(
    product: ProductUi,
    onAddToCartClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onBackClick
        ) {
            Text(text = "← Retour")
        }

        ProductHeader(product = product)

        ProductImage()

        ProductPriceCard(product = product)

        ProductRating(rating = product.rating)

        ProductAvailabilityCard(product = product)

        ProductDescription(
            description = product.description
        )

        ProductWarrantyField(
            warrantyInformation = product.warrantyInformation
        )

        AddToCartButton(
            onClick = onAddToCartClick
        )
    }
}

@Composable
fun ProductHeader(
    product: ProductUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
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

@Composable
fun ProductImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.product_phone),
        contentDescription = "Image du produit",
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ProductPriceCard(
    product: ProductUi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${product.price} €",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "-${product.discountPercentage} %",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun ProductRating(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Note du produit",
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = "$rating / 5",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ProductAvailabilityCard(
    product: ProductUi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = if (product.stock > 0) {
                    "En stock : ${product.stock} unités"
                } else {
                    "Rupture de stock"
                },
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = product.shippingInformation,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ProductDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ProductWarrantyField(
    warrantyInformation: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = warrantyInformation,
        onValueChange = {},
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = "Garantie")
        },
        readOnly = true
    )
}

@Composable
fun AddToCartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Ajouter au panier")
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    ProductExplorerTheme {
        ProductDetailScreen(
            product = sampleProduct(),
            onAddToCartClick = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenOutOfStockPreview() {
    ProductExplorerTheme {
        ProductDetailScreen(
            product = sampleProductOutOfStock(),
            onAddToCartClick = {},
            onBackClick = {}
        )
    }
}