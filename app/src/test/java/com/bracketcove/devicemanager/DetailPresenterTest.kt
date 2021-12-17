package com.bracketcove.devicemanager

import androidx.lifecycle.SavedStateHandle
import com.bracketcove.devicemanager.detail.DetailPresenter
import com.bracketcove.devicemanager.detail.DetailViewEvent
import com.bracketcove.devicemanager.detail.DetailViewModel
import com.bracketcove.devicemanager.detail.IDetailContract
import com.bracketcove.devicemanager.domain.Device
import kotlinx.coroutines.Dispatchers
import org.junit.Test

class DetailPresenterTest {
    val vm = DetailViewModel(SavedStateHandle())
    val presenter = DetailPresenter(
        FakeView(),
        FakeViewModel(),
        FakeDataSource(),
        Dispatchers.Unconfined
    )

    /**
     * Feature starts. Take in the device which was passed into the fragment via safeargs
     * and render it appropriately on the screen
     */
    @Test
    fun onStart() {
        presenter.onEvent(DetailViewEvent.OnStart(fakeDeviceOne))
    }


}

class FakeView : IDetailContract.View {
    internal var navigateToDeviceListCalled = false
    override fun navigateToDeviceList() {
        navigateToDeviceListCalled = true
    }
}

class FakeViewModel : IDetailContract.ViewModel {
    internal var showLoadingCalled = false
    override fun showLoading(boolean: Boolean) {
        showLoadingCalled = true
    }

    internal var setDeviceDataCalled = false
    override fun setDeviceData(device: Device) {
        setDeviceDataCalled = true
    }
}