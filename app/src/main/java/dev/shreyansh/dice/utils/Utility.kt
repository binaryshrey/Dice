package dev.shreyansh.dice.utils

import dev.shreyansh.dice.R

fun setImage(rand : Int): Int {
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