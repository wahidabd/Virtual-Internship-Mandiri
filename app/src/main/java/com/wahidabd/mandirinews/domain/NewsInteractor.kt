package com.wahidabd.mandirinews.domain

import androidx.paging.PagingData
import com.wahidabd.mandirinews.data.source.NewsRepository
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


class NewsInteractor(
    private val repository: NewsRepository
): NewsUseCase {

    override fun headlines(): Flow<PagingData<News>> =
        repository.headlines()

    override fun search(q: String): Flow<PagingData<News>> =
        repository.search(q)
}