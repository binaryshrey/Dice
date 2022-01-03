package dev.shreyansh.dice.ui.game

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        val randomIntOne : Int = Random.nextInt(6) + 1
        val randomIntTwo : Int = Random.nextInt(6) + 1
        _dice1.value = setImage(randomIntOne)
        _dice2.value = setImage(randomIntTwo)
        _result.value = "You Rolled : ${(randomIntOne+randomIntTwo).toString()}"
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
                    Timber.i("okay")
                }
            } else {
                Timber.d("Error: ", request.exception.toString())
            }
        }
    }
}