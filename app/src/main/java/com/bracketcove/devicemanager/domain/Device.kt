package com.bracketcove.devicemanager.domain

data class Device(
    val id: Int,
    val type: String,
    val price: Float,
    val currency: CURRENCY,
    val isFavourite: Boolean,
    val imageUrl: String,
    val description: String,
    val status: STATUS
)

