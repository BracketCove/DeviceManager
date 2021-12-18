package com.bracketcove.devicemanager.detail

import android.util.Log
import com.bracketcove.devicemanager.data.DataSourceResult
import com.bracketcove.devicemanager.GENERIC_ERROR
import com.bracketcove.devicemanager.data.IDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailPresenter(
    private val view: IDetailContract.View,
    private val vm: IDetailContract.ViewModel,
    private val dataSource: IDatasource,
    private val dispatcher: CoroutineContext
): IDetailContract.Presenter, CoroutineScope {

    //for cancellation
    private val jobTracker: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatcher + jobTracker


    override fun onEvent(event: DetailViewEvent) {
        when (event) {
            DetailViewEvent.OnUpPressed -> {
                jobTracker.cancel()
                view.navigateToDeviceList()
            }
            is DetailViewEvent.OnStart -> {
                vm.setDeviceData(event.device)
                vm.showLoading(false)
            }
            DetailViewEvent.OnStop -> jobTracker.cancel()
            is DetailViewEvent.OnFavouriteSelected -> updateFavourite()
        }
    }

    private fun updateFavourite() = launch {
        val isFavourite = !vm.getDeviceIsFavourite()
        val result = dataSource.updateFavourite(isFavourite, vm.getDeviceId())

        when (result) {
            is DataSourceResult.Error -> view.showMessage(GENERIC_ERROR)
            DataSourceResult.OnComplete -> vm.setIconIsFavourite(isFavourite)
            is DataSourceResult.Success -> Log.d("DETAIL_PRESENTER", "Invalid result returned")
        }
    }
}