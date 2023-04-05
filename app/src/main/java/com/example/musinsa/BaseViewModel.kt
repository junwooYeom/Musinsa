package com.example.musinsa

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel

abstract class BaseViewModel<ACTION, S: MavericksState>(
    initialState: S
): MavericksViewModel<S>(initialState) {

    abstract fun onAction(action: ACTION)
}