package com.example.cookUp.ui.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookUp.model.Filter
import com.example.cookUp.model.RecipeCollection
import com.example.cookUp.model.RecipeRepo
import com.example.cookUp.ui.components.FilterBar
import com.example.cookUp.ui.components.CookUpDivider
import com.example.cookUp.ui.components.CookUpScaffold
import com.example.cookUp.ui.components.CookUpSurface
import com.example.cookUp.ui.components.RecipeCollection
import com.example.cookUp.ui.theme.CookUpTheme

@Composable
fun Feed(
    onRecipeClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val recipeCollections = remember { RecipeRepo.getRecipes() }
    CookUpScaffold(
        bottomBar = {
            CookUpBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.FEED.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Feed(
            recipeCollections,
            onRecipeClick,
            Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun Feed(
    recipeCollections: List<RecipeCollection>,
    onRecipeClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    CookUpSurface(modifier = modifier.fillMaxSize()) {
        Box {
            RecipeCollectionList(recipeCollections, onRecipeClick)
            DestinationBar()
        }
    }
}

@Composable
private fun RecipeCollectionList(
    recipeCollections: List<RecipeCollection>,
    onRecipeClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        LazyColumn {
            itemsIndexed(recipeCollections) { index, recipeCollection ->
                if (index > 0) {
                    CookUpDivider(thickness = 2.dp)
                }

                RecipeCollection(
                    recipeCollection = recipeCollection,
                    onRecipeClick = onRecipeClick,
                    index = index
                )
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun HomePreview() {
    CookUpTheme {
        Feed(onRecipeClick = { }, onNavigateToRoute = { })
    }
}
