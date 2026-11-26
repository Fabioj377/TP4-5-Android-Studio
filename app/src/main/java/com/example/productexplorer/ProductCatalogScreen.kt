package com.example.productexplorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productexplorer.ui.theme.ProductExplorerTheme

@Composable
fun ProductCatalogScreen(
    products: List<ProductUi>,
    categories: List<String>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    showOnlyInStock: Boolean,
    onToggleStockFilter: () -> Unit,
    favoriteProductIds: List<Int>,
    onFavoriteClick: (Int) -> Unit,
    onProductClick: (ProductUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        // Ajoute une marge intérieure autour du contenu de la liste
        verticalArrangement = Arrangement.spacedBy(16.dp)
        // Ajoute un espace vertical entre les éléments de la liste
    ) {
        item {
            Text(
                text = "Catalogue produits",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = {
                    Text(text = "Rechercher un produit")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Button(
                onClick = onToggleStockFilter
            ) {
                Text(
                    text = if (showOnlyInStock) {
                        "Afficher tous les produits"
                    } else {
                        "Afficher uniquement les produits en stock"
                    }
                )
            }
        }

        item {
            Text(
                text = "Explorez une sélection de produits disponibles localement.",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        item {
            CategoryRow(categories = categories)
        }

        item {
            Text(
                text = "${products.size} produit(s) affiché(s)",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        items(
            items = products,
            key = { product -> product.id }
        ) { product ->
            ProductListItem(
                product = product,
                isFavorite = favoriteProductIds.contains(product.id),
                onFavoriteClick = {
                    onFavoriteClick(product.id)
                },
                onClick = {
                    onProductClick(product)
                }
            )
        }
    }
}

@Composable
fun ProductListItem(
    product: ProductUi,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = product.brand,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${product.price} € • ★ ${product.rating}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onClick) {
                Text(text = "Voir le produit")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onFavoriteClick) {
                Text(
                    text = if (isFavorite) {
                        "Retirer des favoris"
                    } else {
                        "Ajouter aux favoris"
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryRow(
    categories: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            CategoryChip(label = category)
        }
    }
}

@Preview(
    showBackground = true,
    name = "Catalogue - Light Theme"
)
@Composable
fun ProductCatalogContainerLightPreview() {
    ProductExplorerTheme(darkTheme = false) {
        ProductCatalogContainer(
            products = sampleProducts(),
            categories = sampleCategories(),
            onProductClick = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Catalogue - Dark Theme"
)
@Composable
fun ProductCatalogContainerDarkPreview() {
    ProductExplorerTheme(darkTheme = true) {
        ProductCatalogContainer(
            products = sampleProducts(),
            categories = sampleCategories(),
            onProductClick = {}
        )
    }
}
