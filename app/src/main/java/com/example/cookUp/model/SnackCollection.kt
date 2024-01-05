package com.example.cookUp.model

import androidx.compose.runtime.Immutable

@Immutable
data class RecipeCollection(
    val id: Long,
    val name: String,
    val recipes: List<Recipe>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, Highlight }

/**
 * A fake repo
 */
object RecipeRepo {
    fun getRecipes(): List<RecipeCollection> = recipeCollections
    fun getRecipe(recipeId: Long) = recipes.find { it.id == recipeId }!!
    fun getRelated(@Suppress("UNUSED_PARAMETER") recipeId: Long) = related
    fun getFilters() = filters
    fun getCart() = cart
    fun getSortFilters() = sortFilters
    fun getCategoryFilters() = categoryFilters
    fun getSortDefault() = sortDefault
    fun getLifeStyleFilters() = lifeStyleFilters
}

/**
 * Static data
 */

private val tastyTreats = RecipeCollection(
    id = 1L,
    name = "Найпоширеніші:",
    type = CollectionType.Highlight,
    recipes = recipes.subList(0, 13)
)

private val popular = RecipeCollection(
    id = 2L,
    name = "Популярні на CookUp:",
    recipes = recipes.subList(14, 19)
)

private val wfhFavs = tastyTreats.copy(
    id = 3L,
    name = "Обрані WFH:"
)

private val newlyAdded = popular.copy(
    id = 4L,
    name = "Нещодавно додані:"
)

private val exclusive = tastyTreats.copy(
    id = 5L,
    name = "Тільки на CookUp:"
)

private val also = tastyTreats.copy(
    id = 6L,
    name = "Користувачі також готують:"
)

private val recipeCollections = listOf(
    tastyTreats,
    popular,
    wfhFavs,
    newlyAdded,
    exclusive
)

private val related = listOf(
    also,
    popular
)

private val cart = listOf(
    OrderLine(recipes[4], 2),
    OrderLine(recipes[6], 3),
    OrderLine(recipes[8], 1)
)

@Immutable
data class OrderLine(
    val recipe: Recipe,
    val count: Int
)
