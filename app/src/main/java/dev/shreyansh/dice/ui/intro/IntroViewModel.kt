package dev.shreyansh.dice.ui.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class IntroViewModel : ViewModel() {

    private val _eventGameStart = MutableLiveData<Boolean>()
    val eventGameStart : LiveData<Boolean>
        get() = _eventGameStart

    init {
        Timber.i("HomeViewModel created")
        _eventGameStart.value = false
    }

    fun onGameStart(){
        _eventGameStart.value = true
    }

    fun onGameStartComplete(){
        _eventGameStart.value = false
    }
}