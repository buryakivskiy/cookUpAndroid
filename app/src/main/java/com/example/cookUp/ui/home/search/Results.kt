package com.example.cookUp.ui.home.search

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cookUp.R
import com.example.cookUp.model.Filter
import com.example.cookUp.model.Recipe
import com.example.cookUp.model.recipes
import com.example.cookUp.ui.components.FilterBar
import com.example.cookUp.ui.components.CookUpButton
import com.example.cookUp.ui.components.CookUpDivider
import com.example.cookUp.ui.components.CookUpSurface
import com.example.cookUp.ui.components.RecipeImage
import com.example.cookUp.ui.theme.CookUpTheme
import com.example.cookUp.ui.utils.formatPrice

@Composable
fun SearchResults(
    searchResults: List<Recipe>,
    filters: List<Filter>,
    onRecipeClick: (Long) -> Unit
) {
    Column {
        FilterBar(filters, onShowFilters = {})
        Text(
            text = stringResource(R.string.search_count, searchResults.size),
            style = MaterialTheme.typography.h6,
            color = CookUpTheme.colors.textPrimary,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
        )
        LazyColumn {
            itemsIndexed(searchResults) { index, recipe ->
                SearchResult(recipe, onRecipeClick, index != 0)
            }
        }
    }
}

@Composable
private fun SearchResult(
    recipe: Recipe,
    onRecipeClick: (Long) -> Unit,
    showDivider: Boolean,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onRecipeClick(recipe.id) }
            .padding(horizontal = 24.dp)
    ) {
        val (divider, image, name, tag, priceSpacer, price, add) = createRefs()
        createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
        if (showDivider) {
            CookUpDivider(
                Modifier.constrainAs(divider) {
                    linkTo(start = parent.start, end = parent.end)
                    top.linkTo(parent.top)
                }
            )
        }
        RecipeImage(
            imageUrl = recipe.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image) {
                    linkTo(
                        top = parent.top,
                        topMargin = 16.dp,
                        bottom = parent.bottom,
                        bottomMargin = 16.dp
                    )
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
                    end = add.start,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )
        Text(
            text = recipe.tagline,
            style = MaterialTheme.typography.body1,
            color = CookUpTheme.colors.textHelp,
            modifier = Modifier.constrainAs(tag) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = add.start,
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
        CookUpButton(
            onClick = { /* todo */ },
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(36.dp)
                .constrainAs(add) {
                    linkTo(top = parent.top, bottom = parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = stringResource(R.string.label_add)
            )
        }
    }
}

@Composable
fun NoResults(
    query: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
    ) {
        Image(
            painterResource(R.drawable.empty_state_search),
            contentDescription = null
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.search_no_matches, query),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.search_no_matches_retry),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun SearchResultPreview() {
    CookUpTheme {
        CookUpSurface {
            SearchResult(
                recipe = recipes[0],
                onRecipeClick = { },
                showDivider = false
            )
        }
    }
}
