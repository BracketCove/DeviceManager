package com.bracketcove.devicemanager.di

import com.bracketcove.devicemanager.data.DataSourceResult
import com.bracketcove.devicemanager.data.IDatasource

/**
 * There's no real implementation here since it wasn't part of the requirements, but for simple
 * Http requests I would use HttpUrlConnection. For more complex stuff I would use OkHttp and/or
 * retrofit.
 *
 * Please use the the mock debug build variant for integration testing.
 */
class ProductionDatasource: IDatasource {
    override suspend fun getDevices(): DataSourceResult {
       return DataSourceResult.OnComplete
    }

    override suspend fun updateFavourite(isFavourite: Boolean, id: Int): DataSourceResult {
      return DataSourceResult.OnComplete
    }
}