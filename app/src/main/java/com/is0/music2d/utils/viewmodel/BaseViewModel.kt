package com.is0.music2d.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.is0.music2d.utils.observer.SingleLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import java.util.concurrent.CancellationException

abstract class BaseViewModel : ViewModel() {
    val isLoading = createMutableLiveData(false)
    val error = createSingleLiveData<Throwable>()
    val navigateUp = createSingleLiveData<Unit>()

    protected fun <T> createMutableLiveData(): LiveData<T> = MutableLiveData()

    protected fun <T> createMutableLiveData(initialValue: T): LiveData<T> =
        MutableLiveData(initialValue)

    private fun <T> createSingleLiveData(): LiveData<T> = SingleLiveData<T>()

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

    protected fun setError(error: Throwable) {
        if (error is CancellationException) {
            return
        }
        this.error.postValue(error)
        Timber.e(error)
    }

    protected fun <T> Flow<T>.handleErrors(): Flow<T> =
        catch { error ->
            setError(error)
        }

    protected fun <T> Flow<T>.showLoading(): Flow<T> =
        onStart {
            showLoading(true)
        }

    protected fun <T> Flow<T>.hideLoading(): Flow<T> =
        onEach {
            showLoading(false)
        }.onCompletion {
            showLoading(false)
        }

    protected fun <T> Flow<T>.withStateHandler(): Flow<T> =
        showLoading()
            .handleErrors()
            .hideLoading()

    protected fun showLoading(isLoading: Boolean) {
        this.isLoading.postValue(isLoading)
    }

    fun onBackSelected() {
        navigateUp.postValue(Unit)
    }
}