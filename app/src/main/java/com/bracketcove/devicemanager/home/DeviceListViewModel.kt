package com.bracketcove.devicemanager.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bracketcove.devicemanager.domain.Device

class DeviceListViewModel(private val state: SavedStateHandle) : ViewModel() {
    private lateinit var devices: List<Device>

    private var prompt: String = ""
    private var showLoading: Boolean = false

    internal var subDeviceList: ((List<Device>) -> Unit)? = null
    internal var subShowLoading: ((Boolean) -> Unit)? = null
    internal var subShowPrompt: ((String) -> Unit)? = null

    internal fun setDeviceData(
        devices: List<Device>
    ) {
        this.devices = devices
    }

    internal fun showDeviceData(favourites: Boolean) {
        if (favourites) subDeviceList?.invoke(devices.filter { it.isFavourite })
        else  subDeviceList?.invoke(devices)
    }

    internal fun showPrompt(prompt: String) {
        this.prompt = prompt
        subShowPrompt?.invoke(prompt)
    }

    internal fun showLoading(showLoading: Boolean) {
        this.showLoading = showLoading
        subShowLoading?.invoke(this.showLoading)
    }
}