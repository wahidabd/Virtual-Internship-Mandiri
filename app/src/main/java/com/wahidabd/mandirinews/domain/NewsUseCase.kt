package com.wahidabd.mandirinews.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


interface NewsUseCase {

    fun headlines(): Flow<PagingData<News>>
    fun search(q: String): Flow<PagingData<News>>

}