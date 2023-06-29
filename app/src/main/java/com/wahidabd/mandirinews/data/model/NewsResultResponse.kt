package com.wahidabd.mandirinews.data.model

import com.wahidabd.mandirinews.domain.News


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


data class NewsResultResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)
