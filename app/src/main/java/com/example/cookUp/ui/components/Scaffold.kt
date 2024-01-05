package com.example.cookUp.ui.components

import android.content.res.Resources
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import com.example.cookUp.model.RecipebarManager
import com.example.cookUp.ui.theme.CookUpTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Wrap Material [androidx.compose.material.Scaffold] and set [CookUpTheme] colors.
 */
@Composable
fun CookUpScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable (() -> Unit) = {},
    bottomBar: @Composable (() -> Unit) = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = CookUpTheme.colors.uiBackground,
    drawerContentColor: Color = CookUpTheme.colors.textSecondary,
    drawerScrimColor: Color = CookUpTheme.colors.uiBorder,
    backgroundColor: Color = CookUpTheme.colors.uiBackground,
    contentColor: Color = CookUpTheme.colors.textSecondary,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        isFloatingActionButtonDocked = isFloatingActionButtonDocked,
        drawerContent = drawerContent,
        drawerShape = drawerShape,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = drawerBackgroundColor,
        drawerContentColor = drawerContentColor,
        drawerScrimColor = drawerScrimColor,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}

/**
 * Remember and creates an instance of [CookUpScaffoldState]
 */
@Composable
fun rememberCookUpScaffoldState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    recipebarManager: RecipebarManager = RecipebarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): CookUpScaffoldState = remember(scaffoldState, recipebarManager, resources, coroutineScope) {
    CookUpScaffoldState(scaffoldState, recipebarManager, resources, coroutineScope)
}

/**
 * Responsible for holding [ScaffoldState], handles the logic of showing recipebar messages
 */
@Stable
class CookUpScaffoldState(
    val scaffoldState: ScaffoldState,
    private val recipebarManager: RecipebarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    // Process recipebars coming from RecipebarManager
    init {
        coroutineScope.launch {
            recipebarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = resources.getText(message.messageId)
                    // Notify the RecipebarManager so it can remove the current message from the list
                    recipebarManager.setMessageShown(message.id)
                    // Display the recipebar on the screen. `showRecipebar` is a function
                    // that suspends until the recipebar disappears from the screen
                    scaffoldState.snackbarHostState.showSnackbar(text.toString())
                }
            }
        }
    }
}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
