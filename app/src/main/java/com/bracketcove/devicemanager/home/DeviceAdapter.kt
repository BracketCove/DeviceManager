package com.bracketcove.devicemanager.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bracketcove.devicemanager.R
import com.bracketcove.devicemanager.databinding.ListItemDeviceBinding
import com.bracketcove.devicemanager.domain.DEVICE_TYPE
import com.bracketcove.devicemanager.domain.Device
import com.bracketcove.devicemanager.home.placeholder.PlaceholderContent.PlaceholderItem
import com.bracketcove.devicemanager.toStringValue
import com.bumptech.glide.Glide

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class DeviceAdapter(
    private val eventHandler: ((DeviceListEvent) -> Unit)?
) : ListAdapter<Device, DeviceAdapter.DeviceViewHolder>(
DeviceDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {

        val binding = ListItemDeviceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return DeviceViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        getItem(position).let { item ->
            holder.viewGroup.setOnClickListener {
                eventHandler?.invoke(
                    DeviceListEvent.OnDeviceSelected(position)
                )
            }

            val devIcon = when (item.type) {
                DEVICE_TYPE.ANDROID_PHONE -> R.drawable.ic_phone_android
                DEVICE_TYPE.ANDROID_TABLET -> R.drawable.ic_tablet
                DEVICE_TYPE.ANDROID_WEARABLE -> R.drawable.ic_wearable
                DEVICE_TYPE.IOS_PHONE -> R.drawable.ic_phone_iphone
                DEVICE_TYPE.IOS_TABLET -> R.drawable.ic_tablet
                DEVICE_TYPE.IOS_WEARABLE -> R.drawable.ic_wearable
                DEVICE_TYPE.LAPTOP -> R.drawable.ic_laptop
                DEVICE_TYPE.DESKTOP -> R.drawable.ic_desktop
                DEVICE_TYPE.CAMERA -> R.drawable.ic_camera
                DEVICE_TYPE.THERMOSTAT -> R.drawable.ic_temp
                DEVICE_TYPE.SENSOR -> R.drawable.ic_sensor
                DEVICE_TYPE.INTERCOM -> R.drawable.ic_mic
            }

            Glide.with(holder.itemView)
                .load(devIcon)
                .centerInside()
                .into(holder.deviceIcon)


            holder.deviceName.text = item.name
            holder.deviceStatus.text = item.status.toStringValue()
        }
    }

    inner class DeviceViewHolder(binding: ListItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val viewGroup: ViewGroup = binding.rootListItem
        var deviceIcon: ImageView = binding.deviceIcon
        var deviceName: TextView = binding.textDeviceName
        var deviceStatus: TextView = binding.textDeviceStatus
        var infoIcon: ImageView = binding.infoIcon
    }

}