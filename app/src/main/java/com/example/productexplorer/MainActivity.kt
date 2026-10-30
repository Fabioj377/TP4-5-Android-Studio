package com.example.productexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.productexplorer.ui.theme.ProductExplorerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ProductExplorerTheme {

                var showProductDetail by remember {
                    mutableStateOf(false)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    if (showProductDetail) {
                        ProductDetailScreen(
                            product = sampleProduct(),
                            onAddToCartClick = {
                                // Action plus tard
                            },
                            onBackClick = {
                                showProductDetail = false
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        ProductHomeScreen(
                            featuredProduct = sampleProduct(),
                            onFeaturedProductClick = {
                                showProductDetail = true
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}