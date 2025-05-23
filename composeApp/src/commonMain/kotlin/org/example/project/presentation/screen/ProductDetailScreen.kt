package org.example.project.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack // Using non-automirrored for wider compatibility
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import org.example.project.data.repository.ProductRepository
import org.example.project.model.ProductDetails
import org.example.project.presentation.viewmodel.ProductDetailViewModel
import org.example.project.remote.MockApiService

@OptIn(ExperimentalMaterial3Api::class)
data class ProductDetailScreen(val productId: String) : Screen {
    @Composable
    override fun Content() {
        // Instantiate dependencies (temporary)
        val mockApiService = MockApiService()
        val productRepository = ProductRepository(mockApiService)
        val viewModel = rememberScreenModel { ProductDetailViewModel(productRepository, productId) }
        val uiState by viewModel.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        val titleText = when (val state = uiState) {
                            is ProductDetailViewModel.ProductDetailUiState.Success -> state.productDetails.name
                            else -> "Product Details"
                        }
                        Text(titleText)
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                when (val state = uiState) {
                    is ProductDetailViewModel.ProductDetailUiState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    is ProductDetailViewModel.ProductDetailUiState.Error -> {
                        Text(
                            text = "Error: ${state.message}",
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.error
                        )
                        // TODO: Add a retry button
                    }
                    is ProductDetailViewModel.ProductDetailUiState.NotFound -> {
                        Text(
                            text = "Product not found.",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    is ProductDetailViewModel.ProductDetailUiState.Success -> {
                        ProductDetailsContent(productDetails = state.productDetails)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductDetailsContent(productDetails: ProductDetails) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp) // General spacing between items
    ) {
        // Image
        if (productDetails.images.isNotEmpty()) {
            item {
                AsyncImage(
                    model = productDetails.images.first(),
                    contentDescription = productDetails.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp), // Fixed height for the image
                    contentScale = ContentScale.Crop,
                    placeholder = {
                        Box(modifier = Modifier.fillMaxSize().background(Color.LightGray))
                    },
                    error = {
                        Box(modifier = Modifier.fillMaxSize().background(Color.Red.copy(alpha = 0.1f)))
                    }
                )
            }
        }

        // Name
        item {
            Text(
                text = productDetails.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
        }

        // Price
        item {
            Text(
                text = "$${productDetails.price}", // Assuming price is in USD
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Variants (if any)
        if (productDetails.variants.isNotEmpty()) {
            item {
                Text(
                    text = "Variants: ${productDetails.variants.joinToString()}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

        // Long Description
        item {
            Text(
                text = productDetails.longDescription,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        // Spacer before button
        item {
            Spacer(Modifier.height(16.dp))
        }

        // "Add to Cart" Button
        item {
            Button(
                onClick = { /* TODO: Implement add to cart functionality */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Padding around the button
            ) {
                Text("Add to Cart")
            }
        }
        
        // Spacer at the bottom
        item {
            Spacer(Modifier.height(16.dp))
        }
    }
}
