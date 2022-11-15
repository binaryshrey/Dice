package dev.shreyansh.dice.viewModel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.shreyansh.dice.R
import timber.log.Timber
import kotlin.random.Random

class DiceViewModel(application: Application) : AndroidViewModel(application) {

    private val _eventGameStart = MutableLiveData<Boolean>()
    val eventGameStart : LiveData<Boolean>
        get() = _eventGameStart

    private val _eventGameIntro = MutableLiveData<Boolean>()
    val eventGameIntro : LiveData<Boolean>
        get() = _eventGameIntro

    private val _dice1 = MutableLiveData<Int>()
    val dice1 : LiveData<Int>
        get() = _dice1

    private val _dice2= MutableLiveData<Int>()
    val dice2 : LiveData<Int>
        get() = _dice2

    private val _dice3 = MutableLiveData<Int>()
    val dice3 : LiveData<Int>
        get() = _dice3

    private val _dice4= MutableLiveData<Int>()
    val dice4 : LiveData<Int>
        get() = _dice4

    private val _result= MutableLiveData<String>()
    val result : LiveData<String>
        get() = _result

    init {
        Timber.i("DiceViewModel created")
        _eventGameStart.value = false
        _eventGameIntro.value = false
        _dice1.value = R.drawable.empty_dice
        _dice2.value = R.drawable.empty_dice
        _dice3.value = R.drawable.empty_dice
        _dice4.value = R.drawable.empty_dice
        _result.value = ""
    }

    fun onGameStart(){
        _eventGameStart.value = true
    }

    fun onGameStartComplete(){
        _eventGameStart.value = false
    }

    fun onGameIntro(){
        _eventGameIntro.value = true
    }

    fun onGameIntroComplete(){
        _eventGameIntro.value = false
    }

    fun roll() {
        provideHapticFeedback()
        val randomIntOne : Int = Random.nextInt(6) + 1
        val randomIntTwo : Int = Random.nextInt(6) + 1
        _dice1.value = setImage(randomIntOne)
        _dice2.value = setImage(randomIntTwo)
        _result.value = "You Rolled : ${(randomIntOne+randomIntTwo).toString()}"
    }

    private fun provideHapticFeedback(){
        val vibrator = getApplication<Application>().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // New vibrate method for API Level 26 or higher
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                // Vibrate method for below API Level 26
                vibrator.vibrate(100)
            }
        }
    }

    fun rollBoardOne(){
        provideHapticFeedback()
        val randomIntOne : Int = Random.nextInt(6) + 1
        _dice1.value = setImage(randomIntOne)
        _result.value = "You Rolled : ${(randomIntOne).toString()}"
    }

    fun rollBoardTwo(){
        provideHapticFeedback()
        val randomIntOne : Int = Random.nextInt(6) + 1
        val randomIntTwo : Int = Random.nextInt(6) + 1
        _dice1.value = setImage(randomIntOne)
        _dice2.value = setImage(randomIntTwo)
        _result.value = "You Rolled : ${(randomIntOne+randomIntTwo).toString()}"
    }

    fun rollBoardThree(){
        provideHapticFeedback()
        val randomIntOne : Int = Random.nextInt(6) + 1
        val randomIntTwo : Int = Random.nextInt(6) + 1
        val randomIntThree : Int = Random.nextInt(6) + 1
        _dice1.value = setImage(randomIntOne)
        _dice2.value = setImage(randomIntTwo)
        _dice3.value = setImage(randomIntThree)
        _result.value = "You Rolled : ${(randomIntOne+randomIntTwo+randomIntThree).toString()}"
    }

    fun rollBoardFour(){
        provideHapticFeedback()
        val randomIntOne : Int = Random.nextInt(6) + 1
        val randomIntTwo : Int = Random.nextInt(6) + 1
        val randomIntThree : Int = Random.nextInt(6) + 1
        val randomIntFour : Int = Random.nextInt(6) + 1

        _dice1.value = setImage(randomIntOne)
        _dice2.value = setImage(randomIntTwo)
        _dice3.value = setImage(randomIntThree)
        _dice4.value = setImage(randomIntFour)
        _result.value = "You Rolled : ${(randomIntOne+randomIntTwo+randomIntThree+randomIntFour).toString()}"

    }


    private fun setImage(rand : Int): Int {
        val res = when(rand){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        return res
    }

}