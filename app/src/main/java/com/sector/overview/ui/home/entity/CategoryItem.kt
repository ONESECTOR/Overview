package com.sector.overview.ui.home.entity

import com.sector.domain.entity.kinopoisk.HomeItem

data class CategoryItem(
    val categoryName: String,
    val hasAction: Boolean = false,
    val actionName: String? = null
): HomeItem
