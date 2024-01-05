package com.example.cookUp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cookUp.ui.home.HomeSections
import com.example.cookUp.ui.home.addHomeGraph
import com.example.cookUp.ui.navigation.MainDestinations
import com.example.cookUp.ui.navigation.rememberCookUpNavController
import com.example.cookUp.ui.recipedetail.RecipeDetail
import com.example.cookUp.ui.theme.CookUpTheme

@Preview
@Composable
fun CookUpApp() {
    CookUpTheme {
        val cookUpNavController = rememberCookUpNavController()
        NavHost(
            navController = cookUpNavController.navController,
            startDestination = MainDestinations.HOME_ROUTE
        ) {
            cookUpNavGraph(
                onRecipeSelected = cookUpNavController::navigateToRecipeDetail,
                upPress = cookUpNavController::upPress,
                onNavigateToRoute = cookUpNavController::navigateToBottomBarRoute
            )
        }
    }
}

private fun NavGraphBuilder.cookUpNavGraph(
    onRecipeSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        addHomeGraph(onRecipeSelected, onNavigateToRoute)
    }
    composable(
        "${MainDestinations.SNACK_DETAIL_ROUTE}/{${MainDestinations.SNACK_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.SNACK_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val recipeId = arguments.getLong(MainDestinations.SNACK_ID_KEY)
        RecipeDetail(recipeId, upPress)
    }
}
