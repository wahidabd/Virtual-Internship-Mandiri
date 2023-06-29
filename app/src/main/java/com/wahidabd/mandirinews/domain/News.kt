package com.wahidabd.mandirinews.domain

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 6/28/2023.
 * Github github.com/wahidabd.
 */


data class News(
    val source: Source? = null,
    val author: String? = emptyString(),
    val content: String? = emptyString(),
    val description: String? = emptyString(),
    val publishedAt: String? = emptyString(),
    val title: String? = emptyString(),
    val url: String? = emptyString(),
    val urlToImage: String? = emptyString()
)

data class Source(
    val id: String? = emptyString(),
    val name: String? = emptyString()
)