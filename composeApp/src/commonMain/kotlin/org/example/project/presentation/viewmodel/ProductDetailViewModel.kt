package org.example.project.presentation.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.ProductRepository
import org.example.project.model.ProductDetails

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val productId: String
) : ScreenModel {

    sealed interface ProductDetailUiState {
        object Loading : ProductDetailUiState
        data class Success(val productDetails: ProductDetails) : ProductDetailUiState
        data class Error(val message: String) : ProductDetailUiState
        object NotFound : ProductDetailUiState
    }

    private val _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    init {
        loadProductDetails()
    }

    fun loadProductDetails() {
        coroutineScope.launch {
            _uiState.value = ProductDetailUiState.Loading
            try {
                val details = productRepository.getProductDetails(productId)
                if (details != null) {
                    _uiState.value = ProductDetailUiState.Success(details)
                } else {
                    _uiState.value = ProductDetailUiState.NotFound
                }
            } catch (e: Exception) {
                _uiState.value = ProductDetailUiState.Error("Failed to load product details: ${e.message}")
            }
        }
    }
}
