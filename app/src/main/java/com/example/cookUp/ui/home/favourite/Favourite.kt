package com.example.cookUp.ui.home.favourite

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookUp.R
import com.example.cookUp.model.OrderLine
import com.example.cookUp.model.Recipe
import com.example.cookUp.model.RecipeCollection
import com.example.cookUp.model.RecipeRepo
import com.example.cookUp.model.recipes
import com.example.cookUp.retrofit.Api
import com.example.cookUp.retrofit.dto.User
import com.example.cookUp.ui.components.CookUpDivider
import com.example.cookUp.ui.components.CookUpScaffold
import com.example.cookUp.ui.components.CookUpRecipebar
import com.example.cookUp.ui.components.CookUpSurface
import com.example.cookUp.ui.components.RecipeImage
import com.example.cookUp.ui.components.rememberCookUpScaffoldState
import com.example.cookUp.ui.home.DestinationBar
import com.example.cookUp.ui.home.HomeSections
import com.example.cookUp.ui.home.CookUpBottomBar
import com.example.cookUp.ui.profile.Login
import com.example.cookUp.ui.profile.User
import com.example.cookUp.ui.theme.CookUpTheme
import com.example.cookUp.ui.utils.AuthTokenManager
import com.example.cookUp.ui.utils.formatPrice
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun Cart(
    onRecipeClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = viewModel(factory = FavouriteViewModel.provideFactory())
) {
    val cookUpScaffoldState = rememberCookUpScaffoldState()
    val recipes = listOf(recipes[0], recipes[1], recipes[2])

    CookUpScaffold(
        bottomBar = {
            CookUpBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.CART.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        snackbarHost = {
            SnackbarHost (
                hostState = it,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { recipebarData -> CookUpRecipebar(recipebarData) }
            )
        },
        scaffoldState = cookUpScaffoldState.scaffoldState,
        modifier = modifier
    ) { paddingValues ->
        Cart(
            recipes = recipes,
            removeRecipe = viewModel::removeRecipe,
            onRecipeClick = onRecipeClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun Cart(
    recipes: List<Recipe>,
    removeRecipe: (Long) -> Unit,
    onRecipeClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    CookUpSurface(modifier = modifier.fillMaxSize()) {
        Box {
            CartContent(
                recipes = recipes,
                removeRecipe = removeRecipe,
                onRecipeClick = onRecipeClick,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            DestinationBar(modifier = Modifier.align(Alignment.TopCenter))
        }
    }
}

@Composable
private fun CartContent(
    recipes: List<Recipe>,
    removeRecipe: (Long) -> Unit,
    onRecipeClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources
    val recipeCountFormattedString = remember(recipes.size, resources) {
        resources.getQuantityString(
            R.plurals.cart_order_count,
            recipes.size, recipes.size
        )
    }
    LazyColumn(modifier) {
        item {
            Spacer(
                Modifier.windowInsetsTopHeight(
                    WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                )
            )
            Text(
                text = stringResource(R.string.cart_order_header, recipeCountFormattedString),
                style = MaterialTheme.typography.h6,
                color = CookUpTheme.colors.brand,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .wrapContentHeight()
            )
        }
        items(recipes) { recipe ->
            SwipeDismissItem(
                background = { offsetX ->
                    /*Background color changes from light gray to red when the
                    swipe to delete with exceeds 160.dp*/
                    val backgroundColor = if (offsetX < -160.dp) {
                        CookUpTheme.colors.error
                    } else {
                        CookUpTheme.colors.uiFloated
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(backgroundColor),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Set 4.dp padding only if offset is bigger than 160.dp
                        val padding: Dp by animateDpAsState(
                            if (offsetX > -160.dp) 4.dp else 0.dp
                        )
                        Box(
                            Modifier
                                .width(offsetX * -1)
                                .padding(padding)
                        ) {
                            // Height equals to width removing padding
                            val height = (offsetX + 8.dp) * -1
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height)
                                    .align(Alignment.Center),
                                shape = CircleShape,
                                color = CookUpTheme.colors.error
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Icon must be visible while in this width range
                                    if (offsetX < -40.dp && offsetX > -152.dp) {
                                        // Icon alpha decreases as it is about to disappear
                                        val iconAlpha: Float by animateFloatAsState(
                                            if (offsetX < -120.dp) 0.5f else 1f
                                        )

                                        Icon(
                                            imageVector = Icons.Filled.DeleteForever,
                                            modifier = Modifier
                                                .size(16.dp)
                                                .graphicsLayer(alpha = iconAlpha),
                                            tint = CookUpTheme.colors.uiBackground,
                                            contentDescription = null,
                                        )
                                    }
                                    /*Text opacity increases as the text is supposed to appear in
                                    the screen*/
                                    val textAlpha by animateFloatAsState(
                                        if (offsetX > -144.dp) 0.5f else 1f
                                    )
                                    if (offsetX < -120.dp) {
                                        Text(
                                            text = stringResource(id = R.string.remove_item),
                                            style = MaterialTheme.typography.subtitle1,
                                            color = CookUpTheme.colors.uiBackground,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .graphicsLayer(
                                                    alpha = textAlpha
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
            ) {
                CartItem(
                    recipe = recipe,
                    removeRecipe = removeRecipe,
                    onRecipeClick = onRecipeClick
                )
            }
        }
    }
}

@Composable
fun CartItem(
    recipe: Recipe,
    removeRecipe: (Long) -> Unit,
    onRecipeClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onRecipeClick(recipe.id.toLong()) }
            .background(CookUpTheme.colors.uiBackground)
            .padding(horizontal = 24.dp)

    ) {
        val (divider, image, name, tag, priceSpacer, price, remove) = createRefs()
        createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
        RecipeImage(
            imageUrl = recipe.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.subtitle1,
            color = CookUpTheme.colors.textSecondary,
            modifier = Modifier.constrainAs(name) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = remove.start,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )
        IconButton(
            onClick = { removeRecipe(recipe.id.toLong()) },
            modifier = Modifier
                .constrainAs(remove) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                tint = CookUpTheme.colors.iconSecondary,
                contentDescription = stringResource(R.string.label_remove)
            )
        }
        Text(
            text = recipe.tagline,
            style = MaterialTheme.typography.body1,
            color = CookUpTheme.colors.textHelp,
            modifier = Modifier.constrainAs(tag) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = parent.end,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )
        Spacer(
            Modifier
                .height(8.dp)
                .constrainAs(priceSpacer) {
                    linkTo(top = tag.bottom, bottom = price.top)
                }
        )
        CookUpDivider(
            Modifier.constrainAs(divider) {
                linkTo(start = parent.start, end = parent.end)
                top.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun SummaryItem(
    subtotal: Long,
    shippingCosts: Long,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.cart_summary_header),
            style = MaterialTheme.typography.h6,
            color = CookUpTheme.colors.brand,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .heightIn(min = 56.dp)
                .wrapContentHeight()
        )
        Row(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(R.string.cart_subtotal_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                text = formatPrice(subtotal),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(R.string.cart_shipping_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                text = formatPrice(shippingCosts),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        CookUpDivider()
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(R.string.cart_total_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .wrapContentWidth(Alignment.End)
                    .alignBy(LastBaseline)
            )
            Text(
                text = formatPrice(subtotal + shippingCosts),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        CookUpDivider()
    }
}


