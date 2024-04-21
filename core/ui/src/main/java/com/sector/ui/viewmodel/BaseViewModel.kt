package com.sector.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.core.component.KoinComponent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<STATE : Any, SIDE_EFFECT : Any>(initialState: STATE) :
    ContainerHost<STATE, SIDE_EFFECT>, ViewModel(), KoinComponent {

    override val container = container<STATE, SIDE_EFFECT>(
        initialState = initialState,
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->

            }
        })
}