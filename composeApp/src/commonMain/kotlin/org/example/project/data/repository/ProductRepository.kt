package org.example.project.data.repository

import org.example.project.model.Product
import org.example.project.model.ProductDetails
import org.example.project.remote.ApiService

class ProductRepository(private val apiService: ApiService) {

    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }

    suspend fun getProductDetails(id: String): ProductDetails? {
        return apiService.getProductDetails(id)
    }
}
