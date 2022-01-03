package dev.shreyansh.dice.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _eventGameStart = MutableLiveData<Boolean>()
    val eventGameStart : LiveData<Boolean>
        get() = _eventGameStart

    init {
        Log.i("HomeViewModel","HomeViewModel created")
        _eventGameStart.value = false
    }

    fun onGameStart(){
        _eventGameStart.value = true
    }

    fun onGameStartComplete(){
        _eventGameStart.value = false
    }
}