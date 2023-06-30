package com.wahidabd.mandirinews.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.mandirinews.R
import com.wahidabd.mandirinews.ui.components.Header
import com.wahidabd.mandirinews.ui.components.NewsLoadState
import com.wahidabd.mandirinews.ui.theme.ColorPrimary
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = koinViewModel()
) {
    val searchResult = viewModel.search.value.collectAsLazyPagingItems()
    val listState: LazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(navigator = navigator, title = "Search", isParent = true)
        Divider(color = Color.LightGray)

        SearchBar(autoFocus = true, viewModel = viewModel, onSearch = { viewModel.search() })
        if (viewModel.searchParam.value.trim().isNotEmpty()){
            NewsLoadState(items = searchResult, listState = listState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    autoFocus: Boolean,
    viewModel: SearchViewModel,
    onSearch: () -> Unit,
) {

    var searchInput by remember { mutableStateOf("") }
    var focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)
            .fillMaxWidth()
            .height(48.dp)
    ) {

        LaunchedEffect(key1 = searchInput) {
            if (viewModel.searchParam.value.trim()
                    .isNotEmpty() && viewModel.searchParam.value.trim().length != viewModel.previousSearch.value.trim().length
            ) {
                delay(550)
                onSearch()
                viewModel.previousSearch.value = searchInput.trim()
            }
        }

        TextField(
            value = searchInput,
            onValueChange = { value ->
                searchInput = if (value.trim().isNotEmpty()) value else emptyString()
                viewModel.searchParam.value = searchInput
            },
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester = focusRequester),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search...",
                    color = ColorPrimary.copy(alpha = 0.9F),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            colors = textFieldColors(
                textColor = Color.White.copy(alpha = 0.78F),
                disabledTextColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (viewModel.searchParam.value.trim().isNotEmpty()){
                        focusManager.clearFocus()
                        viewModel.searchParam.value = searchInput
                        if (searchInput != viewModel.previousSearch.value){
                            viewModel.previousSearch.value = searchInput
                            onSearch()
                        }
                    }
                }
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            trailingIcon = {
                LaunchedEffect(Unit) {
                    if (autoFocus) focusRequester.requestFocus()
                }
                Row {
                    AnimatedVisibility(visible = searchInput.trim().isNotEmpty()) {
                        IconButton(onClick = {
                            focusManager.clearFocus()
                            searchInput = ""
                            viewModel.searchParam.value = ""
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                tint = ColorPrimary,
                                contentDescription = null,
                            )
                        }
                    }

                    IconButton(onClick = {
                        if (viewModel.searchParam.value.trim().isNotEmpty()){
                            focusManager.clearFocus()
                            viewModel.searchParam.value = searchInput
                            if (searchInput != viewModel.previousSearch.value){
                                viewModel.previousSearch.value = searchInput
                                onSearch()
                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            tint = ColorPrimary,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}