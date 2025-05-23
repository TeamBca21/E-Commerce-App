package org.example.project.remote

import org.example.project.model.Product
import org.example.project.model.ProductDetails

interface ApiService {
    suspend fun getProducts(): List<Product>
    suspend fun getProductDetails(id: String): ProductDetails?
}
