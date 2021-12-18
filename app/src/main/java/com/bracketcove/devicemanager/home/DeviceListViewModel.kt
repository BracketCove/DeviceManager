package com.bracketcove.devicemanager.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bracketcove.devicemanager.domain.Device

class DeviceListViewModel(private val state: SavedStateHandle) : ViewModel(), IDeviceListContract.ViewModel {
    private var devices: List<Device> = emptyList()
    override fun getDevices(): List<Device> = this.devices

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

    override fun showDeviceData(devices: List<Device>) {
        subDeviceList?.invoke(devices)
    }

    override fun showPrompt(prompt: String) {
        this.prompt = prompt
        subShowPrompt?.invoke(prompt)
    }

    override fun showLoading(showLoading: Boolean) {
        this.showLoading = showLoading
        subShowLoading?.invoke(this.showLoading)
    }

    override fun getDevice(index: Int): Device = this.devices[index]
}