package com.bracketcove.devicemanager.home

import com.bracketcove.devicemanager.domain.Device

interface IDeviceListContract {
    interface Presenter {
        fun onEvent(event: DeviceListEvent)
    }

    interface View {
        fun navigateToDetailFragment(device: Device)
        fun showMessage(message: String)
    }

    interface ViewModel {
        fun setDeviceData(devices: List<Device>)
        fun showDeviceData(devices: List<Device>)
        fun showPrompt(prompt: String)
        fun showLoading(showLoading: Boolean)
        fun getDevice(index: Int) : Device
        fun getDevices() : List<Device>
    }
}

