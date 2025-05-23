package org.example.project.presentation.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.ProductRepository
import org.example.project.model.Product

class ProductListViewModel(private val productRepository: ProductRepository) : ScreenModel {

    sealed interface ProductListUiState {
        object Loading : ProductListUiState
        data class Success(val products: List<Product>) : ProductListUiState
        data class Error(val message: String) : ProductListUiState
    }

    private val _uiState = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        coroutineScope.launch {
            _uiState.value = ProductListUiState.Loading
            try {
                val products = productRepository.getProducts()
                _uiState.value = ProductListUiState.Success(products)
            } catch (e: Exception) {
                _uiState.value = ProductListUiState.Error("Failed to load products: ${e.message}")
            }
        }
    }
}
