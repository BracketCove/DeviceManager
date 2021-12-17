package com.bracketcove.devicemanager.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bracketcove.devicemanager.detail.IDetailContract
import com.bracketcove.devicemanager.domain.Device

class DeviceListViewModel(private val state: SavedStateHandle) : ViewModel(), IDeviceListContract.ViewModel {
    private lateinit var devices: List<Device>

    private var prompt: String = ""
    private var showLoading: Boolean = false

    internal var subDeviceList: ((List<Device>) -> Unit)? = null
    internal var subShowLoading: ((Boolean) -> Unit)? = null
    internal var subShowPrompt: ((String) -> Unit)? = null

    override fun setDeviceData(
        devices: List<Device>
    ) {
        this.devices = devices
    }

    override fun showDeviceData(favourites: Boolean) {
        if (favourites) subDeviceList?.invoke(devices.filter { it.isFavourite })
        else  subDeviceList?.invoke(devices)
    }

    override fun showPrompt(prompt: String) {
        this.prompt = prompt
        subShowPrompt?.invoke(prompt)
    }

    override fun showLoading(showLoading: Boolean) {
        this.showLoading = showLoading
        subShowLoading?.invoke(this.showLoading)
    }
}