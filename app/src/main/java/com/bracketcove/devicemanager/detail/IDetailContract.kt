package com.bracketcove.devicemanager.detail

import com.bracketcove.devicemanager.domain.Device

//allows for easy testing
interface IDetailContract {
    interface Presenter {
        fun onEvent(event: DetailViewEvent)
    }

    interface View {
        fun navigateToDeviceList()
    }

    interface ViewModel {
        fun showLoading(boolean: Boolean)
        fun setDeviceData(device: Device)
    }
}
