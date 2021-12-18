package com.bracketcove.devicemanager.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bracketcove.devicemanager.domain.Device
import com.bracketcove.devicemanager.toStringValue

/**
 * My general approach for jetpack models is to use them exclusively for persistent data across
 * orientation changes and process death. I find it useful to keep the presentation logic out of
 * them as much as possible, and place that logic in a class easy to test with JUnit.
 */
class DetailViewModel(private val state: SavedStateHandle) : ViewModel(), IDetailContract.ViewModel{
    private lateinit var device: Device

    //Rx or LiveData is an option here to but I prefer not to use a library if I have a standard
    //library solution that works
    internal var subShowLoading: ((Boolean) -> Unit)? = null
    internal var subName: ((String) -> Unit)? = null
    internal var subStatus: ((String) -> Unit)? = null
    internal var subType: ((String) -> Unit)? = null
    internal var subDescription: ((String) -> Unit)? = null
    internal var subImageUrl:((String) -> Unit)? = null
    internal var subIconIsFavourite:((Boolean) -> Unit)? = null

    override fun showLoading(boolean: Boolean){
        subShowLoading?.invoke(boolean)
    }
    override fun setDeviceData(device: Device) {
        this.device = device

        subName?.invoke(this.device.name)
        subStatus?.invoke(this.device.status.toStringValue())
        subType?.invoke(this.device.type.toStringValue())
        subDescription?.invoke(this.device.description)
        subImageUrl?.invoke(this.device.imageUrl)
        subIconIsFavourite?.invoke(this.device.isFavourite)
    }

    override fun setIconIsFavourite(isFavourite: Boolean) {
        device.isFavourite = isFavourite
        subIconIsFavourite?.invoke(isFavourite)
    }

    override fun getDeviceId(): Int = device.id
    override fun getDeviceIsFavourite(): Boolean = device.isFavourite

}