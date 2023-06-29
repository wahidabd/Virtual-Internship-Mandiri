package com.wahidabd.mandirinews.data.source

import androidx.paging.PagingData
import com.wahidabd.mandirinews.domain.News
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


interface NewsRepository {

    fun headlines(): Flow<PagingData<News>>
    fun search(q: String): Flow<PagingData<News>>

}