package com.wahidabd.mandirinews.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wahidabd.mandirinews.data.ApiService
import com.wahidabd.mandirinews.domain.News
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


class NewsDataSource(
    private val api: ApiService
) : NewsRepository {

    override fun headlines(): Flow<PagingData<News>> =
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(api) }
        ).flow

    override fun search(q: String): Flow<PagingData<News>> =
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {NewsPagingSource(api, q)}
        ).flow
}