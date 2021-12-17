package com.bracketcove.devicemanager.detail

//allows for easy testing
interface IDetailContract {
    interface Presenter {
        fun onEvent(event: DetailViewEvent)
    }

    interface View {
        fun navigateToDeviceList()
    }
}
