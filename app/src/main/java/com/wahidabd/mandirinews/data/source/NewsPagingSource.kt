package com.wahidabd.mandirinews.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wahidabd.mandirinews.data.ApiService
import com.wahidabd.mandirinews.domain.News
import retrofit2.HttpException
import java.io.IOException


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


class NewsPagingSource(
    private val api: ApiService,
    private val q: String? = null
) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> =
        try {
            val position = params.key ?: 1
            val result = when(q){
                null -> api.headlines(position).articles
                else -> api.search(q, position).articles
            }

            LoadResult.Page(
                data = result,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (result.isEmpty()) null else position.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

}