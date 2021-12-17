package com.bracketcove.devicemanager

import com.bracketcove.devicemanager.domain.DEVICE_TYPE
import com.bracketcove.devicemanager.domain.Device
import com.bracketcove.devicemanager.domain.STATUS
import java.io.IOException


class FakeDataSource: IDatasource {
    var showError = false

    override suspend fun getDevices(): DataSourceResult =
        if (showError) {
            DataSourceResult.Error(IOException())
        } else {
            DataSourceResult.Success(fakeDeviceList)
        }

    override suspend fun updateFavourite(isFavourite: Boolean, id: Int): DataSourceResult =
        if (showError) {
            DataSourceResult.Error(IOException())
        } else {
            DataSourceResult.OnComplete
        }

}

internal val fakeDeviceOne = Device(
    1,
    "My Android Phone",
    DEVICE_TYPE.ANDROID_PHONE,
    true,
    "https://en.wikipedia.org/wiki/Mobile_phone#/media/File:2007Computex_e21Forum-MartinCooper.jpg",
    "This is my Android phone. I absolutely love it except when it drops my damn calls. Also I am" +
            "thoroughly unimpressed with Android 12's obscene level of negative space in ui widgets.",
    STATUS.AVAILABLE,
    "Android 12"
)

internal val fakeDeviceTwo = Device(
    2,
    "My iPad",
    DEVICE_TYPE.IOS_TABLET,
    false,
    "https://en.wikipedia.org/wiki/Mobile_phone#/media/File:2007Computex_e21Forum-MartinCooper.jpg",
    "Lorem ipsum and all the rest",
    STATUS.OFFLINE,
    "iPadOS 13 "
)

internal val fakeDeviceThree = Device(
    3,
    "Front Door Camera",
    DEVICE_TYPE.CAMERA,
    true,
    "https://en.wikipedia.org/wiki/Mobile_phone#/media/File:2007Computex_e21Forum-MartinCooper.jpg",
    "Front door camera in case of delivery people and porch pirates.",
    STATUS.AVAILABLE,
    "Embedded"
)

val fakeDeviceList = listOf(
    fakeDeviceOne,
    fakeDeviceTwo,
    fakeDeviceThree,
    fakeDeviceOne,
    fakeDeviceTwo,
    fakeDeviceThree,
    fakeDeviceOne,
    fakeDeviceTwo,
    fakeDeviceThree,
    fakeDeviceOne,
    fakeDeviceTwo,
    fakeDeviceThree,
    fakeDeviceOne,
    fakeDeviceTwo,
    fakeDeviceThree,
    fakeDeviceOne,
    fakeDeviceTwo,
    fakeDeviceThree,
)