package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
// Modifier import removed as it's not used in the new App.kt
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.presentation.screen.ProductListScreen // Ensure this import is correct

@Composable
// @Preview // Preview removed as requested
fun App() {
    MaterialTheme {
        Navigator(screen = ProductListScreen)
    }
}