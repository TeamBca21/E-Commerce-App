package org.example.project.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetails(
    val id: String,
    val name: String,
    val longDescription: String,
    val images: List<String>,
    val price: Double,
    val variants: List<String>
)
