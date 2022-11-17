package com.is0.music2d.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.is0.music2d.utils.observer.SingleLiveData
import timber.log.Timber
import java.util.concurrent.CancellationException

abstract class BaseViewModel : ViewModel() {
    val isLoading = createMutableLiveData(false)
    val error = createSingleLiveData<Throwable>()
    val navigateUp = createSingleLiveData<Unit>()

    protected fun <T> createMutableLiveData(): LiveData<T> = MutableLiveData()

    protected fun <T> createMutableLiveData(initialValue: T): LiveData<T> =
        MutableLiveData(initialValue)

    protected fun <T> createSingleLiveData(): LiveData<T> = SingleLiveData<T>()

    protected fun <T> LiveData<T>.postValue(value: T) {
        when (this) {
            is MutableLiveData<T> -> postValue(value)
            else -> throw Exception("Not using createMutableLiveData() or createSingleLiveData() to create live data")
        }
    }

    protected fun <T> LiveData<T>.setValue(value: T) {
        when (this) {
            is MutableLiveData<T> -> setValue(value)
            else -> throw Exception("Not using createMutableLiveData() or createSingleLiveData() to create live data")
        }
    }

    fun <T> LiveData<T>.mutateValue(onNewValue: (T) -> T) {
        val newValue = value?.let { onNewValue(it) } ?: throw Exception("Initial value is null")
        setValue(newValue)
    }

    protected open suspend fun onCreated() {
        // Empty
    }

    protected fun setError(error: Throwable) {
        if (error is CancellationException) {
            return
        }
        this.error.postValue(error)
        Timber.e(error)
    }

    protected fun showLoading(isLoading: Boolean) {
        this.isLoading.postValue(isLoading)
    }

    fun onBackSelected() {
        navigateUp.postValue(Unit)
    }
}