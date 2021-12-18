package com.bracketcove.devicemanager

import com.bracketcove.devicemanager.domain.DEVICE_TYPE
import com.bracketcove.devicemanager.domain.STATUS

internal const val GENERIC_ERROR = "An error has occured."

//NOTE: hard coded strings just for demo purposes; would use string resources in production
//for localization
internal fun STATUS.toStringValue(): String = when(this) {
    STATUS.AVAILABLE -> "Available"
    STATUS.OFFLINE -> "Offline"
}

internal fun DEVICE_TYPE.toStringValue(): String = when(this) {
    DEVICE_TYPE.ANDROID_PHONE -> "Android Phone"
    DEVICE_TYPE.ANDROID_TABLET -> "Android Tablet"
    DEVICE_TYPE.ANDROID_WEARABLE -> "Android Wearable"
    DEVICE_TYPE.IOS_PHONE -> "iOS Phone"
    DEVICE_TYPE.IOS_TABLET -> "iOS Tablet"
    DEVICE_TYPE.IOS_WEARABLE -> "iOS Wearable"
    DEVICE_TYPE.LAPTOP -> "Laptop"
    DEVICE_TYPE.DESKTOP -> "Desktop"
    DEVICE_TYPE.CAMERA -> "Camera"
    DEVICE_TYPE.THERMOSTAT -> "Thermostat"
    DEVICE_TYPE.SENSOR -> "Sensor"
    DEVICE_TYPE.INTERCOM -> "Intercom"
}