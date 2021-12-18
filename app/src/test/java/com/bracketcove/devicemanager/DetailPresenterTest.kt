package com.bracketcove.devicemanager

import com.bracketcove.devicemanager.detail.DetailPresenter
import com.bracketcove.devicemanager.detail.DetailViewEvent
import com.bracketcove.devicemanager.detail.IDetailContract
import com.bracketcove.devicemanager.domain.Device
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import org.junit.Test
import org.junit.jupiter.api.BeforeEach

class DetailPresenterTest {

    val view = FakeDetailView()
    val vm = FakeDetailViewModel()
    val datasource = FakeDataSource()
    var presenter = DetailPresenter(
        view,
        vm,
        datasource,
        Dispatchers.Unconfined
    )

    @BeforeEach
    fun setUp() {
        view.navigateToDeviceListCalled = false
        view.showMessageCalled = false

        vm.setDeviceDataCalled = false
        vm.setIconIsFavouriteCalled = false
        vm.showLoadingCalled = false
    }


    /**
     * Feature starts. Take in the device which was passed into the fragment via safeargs
     * and render it appropriately on the screen
     *
     * 1. set device data in vm
     * 2. set showLoading (false)
     */
    @Test
    fun onStart() {
        presenter.onEvent(DetailViewEvent.OnStart(fakeDeviceOne))

        assertTrue(vm.setDeviceDataCalled)
        assertTrue(vm.showLoadingCalled)
    }


    @Test
    fun onFavouriteToggledSuccess() {
        presenter.onEvent(DetailViewEvent.OnFavouriteSelected)

        assertTrue(datasource.updateFavouritesCalled)
        assertTrue(vm.getIconIsFavouriteCalled)
        assertTrue(vm.setIconIsFavouriteCalled)
    }

    @Test
    fun onFavouriteToggledError() {
        datasource.showError = true
        presenter.onEvent(DetailViewEvent.OnFavouriteSelected)

        assertTrue(datasource.updateFavouritesCalled)
        assertTrue(vm.getIconIsFavouriteCalled)
        assertTrue(view.showMessageCalled)
    }

    @Test
    fun onNavigateAction() {
        presenter.onEvent(DetailViewEvent.OnUpPressed)

        assertTrue (view.navigateToDeviceListCalled)
    }

}

class FakeDetailView : IDetailContract.View {
    internal var navigateToDeviceListCalled = false
    override fun navigateToDeviceList() {
        navigateToDeviceListCalled = true
    }

    internal var showMessageCalled = false
    override fun showMessage(message: String) {
        showMessageCalled = true
    }
}

class FakeDetailViewModel : IDetailContract.ViewModel {
    internal var showLoadingCalled = false
    override fun showLoading(boolean: Boolean) {
        showLoadingCalled = true
    }

    internal var setDeviceDataCalled = false
    override fun setDeviceData(device: Device) {
        setDeviceDataCalled = true
    }

    internal var setIconIsFavouriteCalled = false
    override fun setIconIsFavourite(isFavourite: Boolean) {
        setIconIsFavouriteCalled = true
    }

    override fun getDeviceId(): Int {
        return 1
    }

    internal var getIconIsFavouriteCalled = false
    override fun getDeviceIsFavourite(): Boolean {
        getIconIsFavouriteCalled = true
        return false
    }
}