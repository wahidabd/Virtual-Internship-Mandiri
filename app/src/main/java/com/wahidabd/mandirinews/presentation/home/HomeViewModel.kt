package com.wahidabd.mandirinews.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.mandirinews.domain.News
import com.wahidabd.mandirinews.domain.NewsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


class HomeViewModel(
    private val useCase: NewsUseCase
) : ViewModel(){

    private val _headlines = mutableStateOf<Flow<PagingData<News>>>(emptyFlow())
    val headline: State<Flow<PagingData<News>>> get() = _headlines


    fun headlines(){
        _headlines.value = useCase.headlines()
            .cachedIn(viewModelScope)
    }
}