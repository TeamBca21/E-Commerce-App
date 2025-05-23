package org.example.project.remote

import kotlinx.coroutines.delay
import org.example.project.model.Product
import org.example.project.model.ProductDetails

class MockApiService : ApiService {

    companion object {
        private val mockProducts = listOf(
            Product(
                id = "1",
                name = "Laptop Pro 15",
                shortDescription = "High-performance laptop for professionals.",
                imageUrl = "https://example.com/images/laptop_pro_15.png",
                price = 1499.99
            ),
            Product(
                id = "2",
                name = "Smartphone X",
                shortDescription = "Latest generation smartphone with AI features.",
                imageUrl = "https://example.com/images/smartphone_x.png",
                price = 899.50
            ),
            Product(
                id = "3",
                name = "Wireless Headphones II",
                shortDescription = "Noise-cancelling over-ear headphones.",
                imageUrl = "https://example.com/images/headphones_ii.png",
                price = 249.00
            ),
            Product(
                id = "4",
                name = "Smartwatch Series 7",
                shortDescription = "Advanced smartwatch with health tracking.",
                imageUrl = "https://example.com/images/smartwatch_7.png",
                price = 399.00
            )
        )

        private val mockProductDetails = mapOf(
            "1" to ProductDetails(
                id = "1",
                name = "Laptop Pro 15",
                longDescription = "The Laptop Pro 15 features a stunning 15-inch Retina display, powerful Intel Core i9 processor, 32GB RAM, and a 1TB SSD. Ideal for video editing, coding, and demanding applications. Comes with a backlit keyboard and three Thunderbolt 4 ports.",
                images = listOf(
                    "https://example.com/images/laptop_pro_15_front.png",
                    "https://example.com/images/laptop_pro_15_side.png",
                    "https://example.com/images/laptop_pro_15_keyboard.png"
                ),
                price = 1499.99,
                variants = listOf("Color: Space Gray", "Color: Silver", "Storage: 512GB", "Storage: 1TB", "RAM: 16GB", "RAM: 32GB")
            ),
            "2" to ProductDetails(
                id = "2",
                name = "Smartphone X",
                longDescription = "Experience the future with Smartphone X. It boasts a 6.7-inch OLED display, the latest A17 Bionic chip, a triple-camera system with 10x optical zoom, and all-day battery life. 5G connectivity and advanced AI capabilities make this the smartest phone yet.",
                images = listOf(
                    "https://example.com/images/smartphone_x_front.png",
                    "https://example.com/images/smartphone_x_back.png",
                    "https://example.com/images/smartphone_x_camera.png"
                ),
                price = 899.50,
                variants = listOf("Color: Midnight Blue", "Color: Pearl White", "Color: Ruby Red", "Storage: 128GB", "Storage: 256GB", "Storage: 512GB")
            ),
            "3" to ProductDetails(
                id = "3",
                name = "Wireless Headphones II",
                longDescription = "Immerse yourself in sound with Wireless Headphones II. Featuring industry-leading noise cancellation, plush earcups for ultimate comfort, and up to 30 hours of battery life on a single charge. Crystal clear call quality and intuitive touch controls.",
                images = listOf(
                    "https://example.com/images/headphones_ii_profile.png",
                    "https://example.com/images/headphones_ii_folded.png",
                    "https://example.com/images/headphones_ii_case.png"
                ),
                price = 249.00,
                variants = listOf("Color: Black", "Color: Ivory", "Color: Navy Blue")
            ),
             "4" to ProductDetails(
                id = "4",
                name = "Smartwatch Series 7",
                longDescription = "Stay connected and healthy with the Smartwatch Series 7. It features a larger, always-on display, ECG and blood oxygen monitoring, fall detection, and a wide range of fitness tracking modes. Seamlessly integrates with your smartphone.",
                images = listOf(
                    "https://example.com/images/smartwatch_7_face.png",
                    "https://example.com/images/smartwatch_7_band.png",
                    "https://example.com/images/smartwatch_7_side.png"
                ),
                price = 399.00,
                variants = listOf("Size: 41mm", "Size: 45mm", "Band Color: Black", "Band Color: White", "Band Color: Green")
            )
        )
    }

    override suspend fun getProducts(): List<Product> {
        delay(1000L) // Simulate network delay
        return mockProducts
    }

    override suspend fun getProductDetails(id: String): ProductDetails? {
        delay(500L) // Simulate network delay
        return mockProductDetails[id]
    }
}
