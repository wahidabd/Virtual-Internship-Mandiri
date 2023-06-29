package com.wahidabd.mandirinews.ui.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.wahidabd.mandirinews.domain.News
import com.wahidabd.mandirinews.ui.theme.ColorPrimary
import retrofit2.HttpException
import java.io.IOException


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun NewsLoadState(
    items: LazyPagingItems<News>,
    listState: LazyListState
) {


    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 12.dp)
    ) {
        items(items) { news ->
            NewsItem(data = news!!, onClick = {})
        }

        when (items.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Loading"
                        )

                        CircularProgressIndicator(color = ColorPrimary)
                    }
                }
            }

            is LoadState.Error -> {
                val error = items.loadState.refresh as LoadState.Error
                val errorMessage = when (error.error) {
                    is HttpException -> "Sorry, Something went wrong!\nTap to retry"
                    is IOException -> "Connection failed. Tap to retry!"
                    else -> "Failed! Tap to retry!"
                }

                item {
                    Text(
                        text = errorMessage,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        color = ColorPrimary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            else -> {}
        }

        when (items.loadState.append) {
            is LoadState.Error -> {
                val error = items.loadState.refresh as LoadState.Error
                val errorMessage = when (error.error) {
                    is HttpException -> "Sorry, Something went wrong!\nTap to retry"
                    is IOException -> "Connection failed. Tap to retry!"
                    else -> "Failed! Tap to retry!"
                }

                item {
                    Text(
                        text = errorMessage,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        color = ColorPrimary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
    }
}