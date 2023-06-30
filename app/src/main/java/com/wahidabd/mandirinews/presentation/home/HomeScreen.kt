package com.wahidabd.mandirinews.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.mandirinews.R
import com.wahidabd.mandirinews.ui.components.Header
import com.wahidabd.mandirinews.ui.components.NewsItem
import com.wahidabd.mandirinews.ui.components.NewsLoadState
import com.wahidabd.mandirinews.ui.theme.ColorPrimary
import org.koin.androidx.compose.koinViewModel
import retrofit2.HttpException
import java.io.IOException


/**
 * Created by Wahid on 6/28/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel = koinViewModel()
) {

    LaunchedEffect(Unit){
        homeViewModel.headlines()
    }

    val headlines = homeViewModel.headline.value.collectAsLazyPagingItems()
    val listState: LazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(
            navigator = navigator,
            title = stringResource(id = R.string.app_name),
            isParent = false
        )
        Divider(color = Color.LightGray)
        NewsLoadState(items = headlines, listState)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navigator = EmptyDestinationsNavigator)
}