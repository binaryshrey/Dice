package dev.shreyansh.dice.ui.game

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.play.core.review.ReviewManagerFactory
import dev.shreyansh.dice.R
import timber.log.Timber
import kotlin.random.Random

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val _dice1 = MutableLiveData<Int>()
    val dice1 : LiveData<Int>
        get() = _dice1

    private val _dice2= MutableLiveData<Int>()
    val dice2 : LiveData<Int>
        get() = _dice2

    private val _result= MutableLiveData<String>()
    val result : LiveData<String>
        get() = _result

    init {
        Timber.i("GameViewModel created")
        _dice1.value = R.drawable.empty_dice
        _dice2.value = R.drawable.empty_dice
        _result.value = ""
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
    fun showRatingDialog(){
        val reviewManager = ReviewManagerFactory.create(getApplication())
        val requestReviewFlow = reviewManager.requestReviewFlow()
        requestReviewFlow.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                val reviewInfo = request.result
                val flow = reviewManager.launchReviewFlow(getApplication(),reviewInfo)
                flow.addOnCompleteListener {
                    Timber.i("review invoked")
                }
            } else {
                Timber.d("Error: ", request.exception.toString())
            }
        }
    }
}