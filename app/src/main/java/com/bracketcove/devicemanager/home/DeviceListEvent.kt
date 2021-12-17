package com.bracketcove.devicemanager.home

sealed class DeviceListEvent {
    object OnStart : DeviceListEvent()
    object OnStop : DeviceListEvent()
    data class OnDeviceSelected(val itemIndex: Int) : DeviceListEvent()
    data class OnSearchTextInput(val input: String) : DeviceListEvent()
}
