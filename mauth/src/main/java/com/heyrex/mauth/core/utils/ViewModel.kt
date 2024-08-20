package com.heyrex.mauth.core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <S> ViewModel.async(
    flow: Flow<Result<S>>,
    crossinline success: (S) -> Unit,
    crossinline error: (Any) -> Unit
) =
    viewModelScope.launch {
        flow.collect { result ->
            result.fold(
                onSuccess = success,
                onFailure = {
                    if (it is RequestFailure) {
                        error(it.errorManager())
                    } else {
                        error(it)
                    }
                }
            )
        }
    }