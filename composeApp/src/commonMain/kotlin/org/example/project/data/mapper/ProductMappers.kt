package org.example.project.data.mapper

import org.example.project.data.remote.dto.DummyProductDto
import org.example.project.model.Product
import org.example.project.model.ProductDetails

fun DummyProductDto.toProduct(): Product {
    return Product(
        id = this.id.toString(),
        name = this.title,
        shortDescription = this.description,
        imageUrl = this.thumbnail ?: "", // Or a default placeholder image URL
        price = this.price
    )
}

fun DummyProductDto.toProductDetails(): ProductDetails {
    return ProductDetails(
        id = this.id.toString(),
        name = this.title,
        longDescription = this.description,
        images = this.images ?: emptyList(),
        price = this.price,
        variants = this.tags ?: emptyList() // Using tags as variants as per DummyJSON structure
    )
}
