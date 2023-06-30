package com.wahidabd.mandirinews.presentation.search

import androidx.compose.runtime.MutableState
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


/**
 * Created by Wahid on 6/30/2023.
 * Github github.com/wahidabd.
 */


class SearchViewModel(
    private val useCase: NewsUseCase
) : ViewModel() {

    private val _search: MutableState<Flow<PagingData<News>>> = mutableStateOf(emptyFlow())
    val search: State<Flow<PagingData<News>>> get() = _search

    var searchParam = mutableStateOf("")
    var searchParamState: State<String> = searchParam
    var previousSearch = mutableStateOf("")

    fun search() {
        if (searchParam.value.isNotEmpty()) {
            _search.value = useCase.search(searchParam.value).cachedIn(viewModelScope)
        }
    }

}