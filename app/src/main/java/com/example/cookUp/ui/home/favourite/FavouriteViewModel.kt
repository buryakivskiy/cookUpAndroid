package com.example.cookUp.ui.home.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookUp.model.OrderLine
import com.example.cookUp.model.RecipeRepo
import com.example.cookUp.model.RecipebarManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Holds the contents of the cart and allows changes to it.
 *
 * TODO: Move data to Repository so it can be displayed and changed consistently throughout the app.
 */
class FavouriteViewModel(
    private val recipebarManager: RecipebarManager,
    recipeRepository: RecipeRepo
) : ViewModel() {

    private val _orderLines: MutableStateFlow<List<OrderLine>> =
        MutableStateFlow(recipeRepository.getCart())
    val orderLines: StateFlow<List<OrderLine>> get() = _orderLines

    // Logic to show errors every few requests
    private var requestCount = 0
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0

    fun removeRecipe(recipeId: Long) {
        _orderLines.value = _orderLines.value.filter { it.recipe.id != recipeId }
    }


    /**
     * Factory for CartViewModel that takes RecipebarManager as a dependency
     */
    companion object {
        fun provideFactory(
            recipebarManager: RecipebarManager = RecipebarManager,
            recipeRepository: RecipeRepo = RecipeRepo
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavouriteViewModel(recipebarManager, recipeRepository) as T
            }
        }
    }
}
