package com.is0.music2d.utils.observer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@Composable
fun <T> LiveData<T>.observe(action: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(this, lifecycleOwner) {
        val observer = Observer<T> { data -> data?.let(action) }
        observe(lifecycleOwner, observer)
        onDispose { removeObserver(observer) }
    }
}