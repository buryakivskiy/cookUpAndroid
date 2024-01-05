package com.example.cookUp.model

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * A fake repo for searching.
 */
object SearchRepo {
    fun getCategories(): List<SearchCategoryCollection> = searchCategoryCollections
    fun getSuggestions(): List<SearchSuggestionGroup> = searchSuggestions

    suspend fun search(query: String): List<Recipe> = withContext(Dispatchers.Default) {
        delay(200L) // simulate an I/O delay
        recipes.filter { it.name.contains(query, ignoreCase = true) }
    }
}

@Immutable
data class SearchCategoryCollection(
    val id: Long,
    val name: String,
    val categories: List<SearchCategory>
)

@Immutable
data class SearchCategory(
    val name: String,
    val imageUrl: String
)

@Immutable
data class SearchSuggestionGroup(
    val id: Long,
    val name: String,
    val suggestions: List<String>
)

/**
 * Static data
 */

private val searchCategoryCollections = listOf(
    SearchCategoryCollection(
        id = 0L,
        name = "Категорії",
        categories = listOf(
            SearchCategory(
                name = "Чіпси та крекери",
                imageUrl = "https://source.unsplash.com/UsSdMZ78Q3E"
            ),
            SearchCategory(
                name = "З фруктами",
                imageUrl = "https://source.unsplash.com/SfP1PtM9Qa8"
            ),
            SearchCategory(
                name = "Десерти",
                imageUrl = "https://source.unsplash.com/_jk8KIyN_uA"
            ),
            SearchCategory(
                name = "Горішки",
                imageUrl = "https://source.unsplash.com/UsSdMZ78Q3E"
            )
        )
    ),
    SearchCategoryCollection(
        id = 1L,
        name = "Стиль життя",
        categories = listOf(
            SearchCategory(
                name = "Органічні",
                imageUrl = "https://source.unsplash.com/7meCnGCJ5Ms"
            ),
            SearchCategory(
                name = "Без глютену",
                imageUrl = "https://source.unsplash.com/m741tj4Cz7M"
            ),
            SearchCategory(
                name = "Палео",
                imageUrl = "https://source.unsplash.com/dt5-8tThZKg"
            ),
            SearchCategory(
                name = "Вегани",
                imageUrl = "https://source.unsplash.com/ReXxkS1m1H0"
            ),
            SearchCategory(
                name = "Вегетеріанці",
                imageUrl = "https://source.unsplash.com/IGfIGP5ONV0"
            ),
            SearchCategory(
                name = "Усі 30",
                imageUrl = "https://source.unsplash.com/9MzCd76xLGk"
            )
        )
    )
)

private val searchSuggestions = listOf(
    SearchSuggestionGroup(
        id = 0L,
        name = "Recent searches",
        suggestions = listOf(
            "Cheese",
            "Apple Sauce"
        )
    ),
    SearchSuggestionGroup(
        id = 1L,
        name = "Popular searches",
        suggestions = listOf(
            "Organic",
            "Gluten Free",
            "Paleo",
            "Vegan",
            "Vegitarian",
            "Whole30"
        )
    )
)
