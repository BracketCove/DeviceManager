package com.bracketcove.devicemanager.home

import androidx.recyclerview.widget.DiffUtil
import com.bracketcove.devicemanager.domain.Device

class DeviceDiffUtil: DiffUtil.ItemCallback<Device>() {
    override fun areItemsTheSame(
        oldItem: Device,
        newItem: Device
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Device,
        newItem: Device
    ): Boolean {
        return oldItem.id == newItem.id
    }
}