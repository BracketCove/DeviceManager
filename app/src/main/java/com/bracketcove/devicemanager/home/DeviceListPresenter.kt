package com.bracketcove.devicemanager.home

import android.util.Log
import com.bracketcove.devicemanager.data.DataSourceResult
import com.bracketcove.devicemanager.GENERIC_ERROR
import com.bracketcove.devicemanager.data.IDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DeviceListPresenter (
    private val view: IDeviceListContract.View,
    private val vm: IDeviceListContract.ViewModel,
    private val dataSource: IDatasource,
    private val dispatcher: CoroutineContext
        ) : IDeviceListContract.Presenter, CoroutineScope {

    //for cancellation
    private val jobTracker: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatcher + jobTracker

    override fun onEvent(event: DeviceListEvent) {
        when (event) {
            is DeviceListEvent.OnDeviceSelected -> onDeviceSelected(event.itemIndex)
            is DeviceListEvent.OnSearchTextInput -> onSearchText(event.input)
            DeviceListEvent.OnStart -> onStart()
            DeviceListEvent.OnStop -> jobTracker.cancel()
        }
    }

    private fun onStart() = launch {
        val result = dataSource.getDevices()

        when (result) {
            is DataSourceResult.Error -> vm.showPrompt(GENERIC_ERROR)
            DataSourceResult.OnComplete -> Log.d("DEVICE_LIST_PRESENTER", "Invalid result returned")
            is DataSourceResult.Success -> {
                if (result.devices.isEmpty()) {
                    vm.showPrompt("Could not find any devices.")
                } else {
                    vm.setDeviceData(result.devices)
                    vm.showDeviceData(result.devices)
                }

                vm.showLoading(false)
            }
        }
    }

    private fun onDeviceSelected(index: Int) {
        view.navigateToDetailFragment(
            vm.getDevice(index)
        )
    }

    private fun onSearchText(target: String) {
        vm.showDeviceData(
            vm.getDevices().filter { it.name.contains(target, true) }
        )
    }
}