package org.example.project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DummyProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String? = null,
    val images: List<String>? = null,
    val tags: List<String>? = null,
    // Add other fields if they might be useful later, like:
    // val category: String? = null,
    // val brand: String? = null,
    // val rating: Double? = null,
    // val stock: Int? = null
)
