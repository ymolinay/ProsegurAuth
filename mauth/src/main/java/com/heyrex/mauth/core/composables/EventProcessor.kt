package com.heyrex.mauth.core.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.heyrex.mauth.ui.EventDelegate

@Composable
fun <E> EventProcessor(
    viewModelEventDelegate: EventDelegate<E>,
    content: @Composable (E?) -> Unit
) {

    val value = viewModelEventDelegate.viewEvents.collectAsState(initial = null).value

    content(value)

    LaunchedEffect(value) {
        if (value != null) {
            viewModelEventDelegate.resetEvent()
        }
    }

}