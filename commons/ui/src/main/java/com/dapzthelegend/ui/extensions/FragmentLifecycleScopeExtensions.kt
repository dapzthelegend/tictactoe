package com.dapzthelegend.ui.extensions

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

/**
 * Adds collector to list of flow's consumers. Collect latest values from producer
 * when activity is active.
 *
 * @param stateFlow The stateflow
 * @param collector The collector the will receive the events.
 *
 * @see LifecycleCoroutineScope.collect
 */
fun<T> LifecycleCoroutineScope.collect(stateFlow: StateFlow<T>, collector: (T) -> Unit){
    this.launchWhenStarted {
        stateFlow.collectLatest {
            it?.let{ t -> collector(t) }
        }
    }
}

/**
 * Adds collector to list of flow's consumers. Collect latest values from producer
 * when activity is active.
 *
 * @param stateFlow The stateflow
 * @param collector The collector the will receive the events.
 *
 * @see LifecycleCoroutineScope.collect
 */
fun<T> LifecycleCoroutineScope.collect(stateFlow: MutableStateFlow<T>, collector: (T) -> Unit){
    this.launchWhenStarted {
        stateFlow.collectLatest {
            it?.let{ t -> collector(t) }
        }
    }
}
