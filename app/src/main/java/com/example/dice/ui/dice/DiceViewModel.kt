package com.example.dice.ui.dice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dice.R
import kotlin.random.Random

class DiceViewModel : ViewModel() {

    private val _dice  = MutableLiveData<Int>()
    val dice : LiveData<Int>
        get() = _dice

    init {
        //Log.i("DiceViewModel", "DiceViewModel called")
        _dice.value =  R.drawable.empty_dice
    }

    override fun onCleared() {
        super.onCleared()
        //Log.i("DiceViewModel", "ViewModel destroyed")
    }

    fun rollDice() {
        val randomInteger = Random.nextInt(6) + 1
        _dice.value = when(randomInteger){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}