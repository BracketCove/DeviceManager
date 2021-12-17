package com.bracketcove.devicemanager.detail

import com.bracketcove.devicemanager.IDatasource
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class DetailPresenter(
    view: IDetailContract.View,
    vm: IDetailContract.ViewModel,
    dataSource: IDatasource,
    dispatcher: CoroutineContext
): IDetailContract.Presenter {
    override fun onEvent(event: DetailViewEvent) {
        TODO("Not yet implemented")
    }
}