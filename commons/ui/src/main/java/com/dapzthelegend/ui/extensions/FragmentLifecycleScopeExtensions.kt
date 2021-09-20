package com.dapzthelegend.ui.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.dapzthelegend.ui.flow.SingleFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Adds collector to list of flow's consumers. Collect latest values from producer
 * when activity is active.
 *
 * @param stateFlow The stateflow
 * @param collector The collector the will receive the events.
 */
fun <T> Fragment.collect(stateFlow: MutableStateFlow<T?>, collector: (T) -> Unit) {
    this.lifecycleScope.launchWhenStarted {
        stateFlow.collectLatest {
            it?.let { t -> collector(t) }
        }
    }
}

/**
 * Adds collector to list of flow's consumers. Collect latest values from producer
 * when activity is active.
 *
 * @param stateFlow The stateflow
 * @param collector The collector the will receive the events.
 */
fun <T> Fragment.collect(stateFlow: StateFlow<T?>, collector: (T) -> Unit) {
    this.lifecycleScope.launchWhenStarted {
        stateFlow.collectLatest {
            it?.let { t -> collector(t) }
        }
    }
}

/**
 * Adds collector to list of flow's consumers. Collect latest values from producer
 * when activity is active.
 *
 * @param flow The flow
 * @param collector The collector the will receive the events.
 */
fun <T> Fragment.collect(flow: SingleFlow<T>, lifecycle: Lifecycle, collector: (T) -> Unit) {
    this.viewLifecycleOwner.lifecycleScope.launch {
        flow.collect(lifecycle, collector)
    }
}
