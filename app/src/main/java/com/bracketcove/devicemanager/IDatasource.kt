package com.bracketcove.devicemanager

import com.bracketcove.devicemanager.domain.Device

interface IDatasource {
    suspend fun getDevices(): DataSourceResult
    suspend fun updateFavourite(isFavourite: Boolean, id: Int) : DataSourceResult
}

sealed class DataSourceResult {
    data class Success(val devices: List<Device>) : DataSourceResult()
    data class Error(val e: Exception) : DataSourceResult()
    object OnComplete: DataSourceResult()
}