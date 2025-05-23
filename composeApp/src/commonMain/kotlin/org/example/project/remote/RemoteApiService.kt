package org.example.project.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.data.mapper.toProduct
import org.example.project.data.mapper.toProductDetails
import org.example.project.data.remote.dto.DummyProductDto
import org.example.project.data.remote.dto.DummyProductListDto
import org.example.project.model.Product
import org.example.project.model.ProductDetails

class RemoteApiService(private val httpClient: HttpClient) : ApiService {

    private val baseUrl = "https://dummyjson.com"

    override suspend fun getProducts(): List<Product> {
        return try {
            val response = httpClient.get("$baseUrl/products")
            // Check if response is successful, though Ktor often throws exceptions for non-2xx
            // For now, assume body() will throw if not successful or parse error
            val productListDto = response.body<DummyProductListDto>()
            productListDto.products.map { it.toProduct() }
        } catch (e: Exception) {
            // Log the error or handle it more gracefully
            println("Error fetching products: ${e.message}")
            emptyList() // Return empty list on error
        }
    }

    override suspend fun getProductDetails(id: String): ProductDetails? {
        return try {
            val response = httpClient.get("$baseUrl/products/$id")
            val productDto = response.body<DummyProductDto>()
            productDto.toProductDetails()
        } catch (e: Exception) {
            println("Error fetching product details for id $id: ${e.message}")
            null // Return null on error
        }
    }
}
