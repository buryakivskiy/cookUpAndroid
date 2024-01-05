package com.example.cookUp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
class Filter(
    val name: String,
    enabled: Boolean = false,
    val icon: ImageVector? = null
) {
    val enabled = mutableStateOf(enabled)
}
val filters = listOf(
    Filter(name = "Дієтичні"),
    Filter(name = "Мʼясні"),
    Filter(name = "Молочні"),
    Filter(name = "Солодкі"),
    Filter(name = "Мучні")
)

val sortFilters = listOf(
    Filter(name = "Android's вибране", icon = Icons.Filled.Android),
    Filter(name = "Рейтинг", icon = Icons.Filled.Star),
    Filter(name = "За алфавітом", icon = Icons.Filled.SortByAlpha)
)

val categoryFilters = listOf(
    Filter(name = "Чіпси та крекери"),
    Filter(name = "З фруктами"),
    Filter(name = "Десерти"),
    Filter(name = "Горішки")
)
val lifeStyleFilters = listOf(
    Filter(name = "Органічні"),
    Filter(name = "Без глютену"),
    Filter(name = "Без лактози"),
    Filter(name = "Солодощі"),
    Filter(name = "Мучні")
)

var sortDefault = sortFilters.get(0).name
