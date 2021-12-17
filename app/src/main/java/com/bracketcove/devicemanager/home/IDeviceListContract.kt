package com.bracketcove.devicemanager.home

import com.bracketcove.devicemanager.domain.Device

interface IDeviceListContract {
    interface Presenter {
        fun onEvent(event: DeviceListEvent)
    }

    interface View {
        fun navigateToDetailFragment(device: Device)
    }

    interface ViewModel {
        fun setDeviceData(devices: List<Device>)
        fun showDeviceData(favourites: Boolean)
        fun showPrompt(prompt: String)
        fun showLoading(showLoading: Boolean)
    }
}

