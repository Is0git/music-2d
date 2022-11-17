package com.is0.music2d.utils.observer

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveData<T> : MutableLiveData<T>() {
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { data ->
            if (data != null) {
                observer.onChanged(data)
                value = null
            }
        }
    }
}