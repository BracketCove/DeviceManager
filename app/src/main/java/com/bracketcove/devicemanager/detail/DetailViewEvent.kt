package com.bracketcove.devicemanager.detail

import com.bracketcove.devicemanager.domain.Device

sealed class DetailViewEvent {
    data class OnStart(val device: Device) : DetailViewEvent()
    object OnStop : DetailViewEvent()
    object OnUpPressed: DetailViewEvent()
    object OnFavouriteSelected : DetailViewEvent()
}
