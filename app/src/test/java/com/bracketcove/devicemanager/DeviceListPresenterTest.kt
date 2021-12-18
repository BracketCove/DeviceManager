package com.bracketcove.devicemanager

import com.bracketcove.devicemanager.domain.Device
import com.bracketcove.devicemanager.home.DeviceListEvent
import com.bracketcove.devicemanager.home.DeviceListPresenter
import com.bracketcove.devicemanager.home.IDeviceListContract
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.BeforeEach

class DeviceListPresenterTest {
    val view = FakeListView()
    val vm = FakeListViewModel()
    val datasource = FakeDataSource()
    val presenter = DeviceListPresenter(
        view,
        vm,
        datasource,
        Dispatchers.Unconfined
    )

    @BeforeEach
    fun setUp() {
        view.navigateToDetailCalled = false
        view.showMessageCalled = false

        vm.setDeviceDataCalled = false
        vm.showDeviceDataCalled = false
        vm.showPromptCalled = false
        vm.showLoadingCalled = false
        vm.getDeviceCalled = false
    }

    /**
     * This is my first time implementing a search bar so I'll have to brainstorm a bit.
     * - target: inputted user text to search for
     * - N: entire dataset
     * - result: the list of devices which we want to display
     *
     * Search algorithm:
     * 1. get devices list from vm
     * 2. filter devices for:
     *      - device.name contains substring target
     * 3. give filter list to vm
     */
    @Test
    fun onSearch() {
        presenter.onEvent(
            DeviceListEvent.OnSearchTextInput("phone")
        )

        assertTrue(vm.getDevicesCalled)
        assertTrue(!vm.setDeviceDataCalled)
        assertTrue(vm.showDeviceDataCalled)
        assertTrue(!vm.showLoadingCalled)
    }

    @Test
    fun onStartDataReturned() {
        presenter.onEvent(DeviceListEvent.OnStart)

        assertTrue(datasource.getDevicesCalled)
        assertTrue(vm.setDeviceDataCalled)
        assertTrue(vm.showDeviceDataCalled)
        assertTrue(vm.showLoadingCalled)
    }

    @Test
    fun onStartEmptyListReturned() = runTest {
        datasource.emptyList = true
        presenter.onEvent(DeviceListEvent.OnStart)

        assertTrue(datasource.getDevicesCalled)
        assertTrue(!vm.setDeviceDataCalled)
        assertTrue(!vm.showDeviceDataCalled)
        assertTrue(vm.showPromptCalled)
        assertTrue(vm.showLoadingCalled)
    }

    @Test
    fun onStartError() {
        presenter.onEvent(DeviceListEvent.OnStart)

        assertTrue(datasource.getDevicesCalled)
        assertTrue(vm.setDeviceDataCalled)
        assertTrue(vm.showLoadingCalled)
    }

    @Test
    fun onNavigateToDetail() {
        presenter.onEvent(DeviceListEvent.OnDeviceSelected(1))

        assertTrue(vm.getDeviceCalled)
        assertTrue(view.navigateToDetailCalled)
    }
}

class FakeListView : IDeviceListContract.View {
    internal var navigateToDetailCalled = false
    override fun navigateToDetailFragment(device: Device) {
        navigateToDetailCalled = true
    }

    internal var showMessageCalled = false

    override fun showMessage(message: String) {
        showMessageCalled = true
    }
}

class FakeListViewModel : IDeviceListContract.ViewModel {
    internal var setDeviceDataCalled = false
    override fun setDeviceData(devices: List<Device>) {
        setDeviceDataCalled = true
    }

    internal var getDeviceCalled = false
    override fun getDevice(index: Int): Device {
        getDeviceCalled = true
        return fakeDeviceOne
    }

    internal var getDevicesCalled = false
    override fun getDevices(): List<Device> {
        getDevicesCalled = true
        return fakeDeviceList
    }

    internal var showDeviceDataCalled = false
    override fun showDeviceData(devices: List<Device>) {
        showDeviceDataCalled = true
    }

    internal var showPromptCalled = false
    override fun showPrompt(prompt: String) {
        showPromptCalled = true
    }

    internal var showLoadingCalled = false
    override fun showLoading(boolean: Boolean) {
        showLoadingCalled = true
    }
}