package com.example.cookUp.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cookUp.ui.theme.CookUpTheme


@Composable
fun CookUpRecipebar(
    recipebarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = MaterialTheme.shapes.small,
    backgroundColor: Color = CookUpTheme.colors.uiBackground,
    contentColor: Color = CookUpTheme.colors.textSecondary,
    actionColor: Color = CookUpTheme.colors.brand,
    elevation: Dp = 6.dp
) {
    Snackbar(
        snackbarData = recipebarData,
        modifier = modifier,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        actionColor = actionColor,
        elevation = elevation
    )
}
