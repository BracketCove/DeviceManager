package com.bracketcove.devicemanager.di

import android.content.Context
import com.bracketcove.devicemanager.detail.DetailPresenter
import com.bracketcove.devicemanager.detail.IDetailContract
import com.bracketcove.devicemanager.home.DeviceListPresenter
import com.bracketcove.devicemanager.home.IDeviceListContract
import kotlinx.coroutines.Dispatchers

/**
 * Note: In small-medium projects I don't like using any libraries for DI as the code is faster
 * to write by hand; but I have used Dagger 2 in the past.
 */
internal fun buildDeviceListLogic(
    context: Context,
    view: IDeviceListContract.View,
    vm: IDeviceListContract.ViewModel
): DeviceListPresenter = DeviceListPresenter(
    view,
    vm,
    ProductionDatasource(),
    Dispatchers.Main
)

internal fun buildDetailLogic(
    context: Context,
    view: IDetailContract.View,
    vm: IDetailContract.ViewModel
): DetailPresenter = DetailPresenter(
    view,
    vm,
    ProductionDatasource(),
    Dispatchers.Main
)