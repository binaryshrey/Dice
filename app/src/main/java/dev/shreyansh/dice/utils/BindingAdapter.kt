package dev.shreyansh.dice.utils


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import dev.shreyansh.dice.R

@BindingAdapter("app:imageResource")
fun setResultImage(imageView: ImageView, result: String?) {
    result?.let {
       when(result){
           "1"->imageView.setImageResource(R.drawable.ic_group_1)
           "2"->imageView.setImageResource(R.drawable.ic_group_2)
           "3"->imageView.setImageResource(R.drawable.ic_group_3)
           "4"->imageView.setImageResource(R.drawable.ic_group_4)
           "5"->imageView.setImageResource(R.drawable.ic_group_5)
           "6"->imageView.setImageResource(R.drawable.ic_group_6)
           "7"->imageView.setImageResource(R.drawable.ic_group_7)
           "8"->imageView.setImageResource(R.drawable.ic_group_8)
           "9"->imageView.setImageResource(R.drawable.ic_group_9)
           "10"->imageView.setImageResource(R.drawable.ic_group_10)
           "11"->imageView.setImageResource(R.drawable.ic_group_11)
           "12"->imageView.setImageResource(R.drawable.ic_group_12)
           "13"->imageView.setImageResource(R.drawable.ic_group_13)
           "14"->imageView.setImageResource(R.drawable.ic_group_14)
           "15"->imageView.setImageResource(R.drawable.ic_group_15)
           "16"->imageView.setImageResource(R.drawable.ic_group_16)
           "17"->imageView.setImageResource(R.drawable.ic_group_17)
           "18"->imageView.setImageResource(R.drawable.ic_group_18)
           "19"->imageView.setImageResource(R.drawable.ic_group_19)
           "20"->imageView.setImageResource(R.drawable.ic_group_20)
           "21"->imageView.setImageResource(R.drawable.ic_group_21)
           "22"->imageView.setImageResource(R.drawable.ic_group_22)
           "23"->imageView.setImageResource(R.drawable.ic_group_23)
           "24"->imageView.setImageResource(R.drawable.ic_group_24)
           "25"->imageView.setImageResource(R.drawable.ic_group_25)
           "26"->imageView.setImageResource(R.drawable.ic_group_26)
           "27"->imageView.setImageResource(R.drawable.ic_group_27)
           "28"->imageView.setImageResource(R.drawable.ic_group_28)
           "29"->imageView.setImageResource(R.drawable.ic_group_29)
           "30"->imageView.setImageResource(R.drawable.ic_group_30)
           "31"->imageView.setImageResource(R.drawable.ic_group_31)
           "32"->imageView.setImageResource(R.drawable.ic_group_32)
           "33"->imageView.setImageResource(R.drawable.ic_group_33)
           "34"->imageView.setImageResource(R.drawable.ic_group_34)
           "35"->imageView.setImageResource(R.drawable.ic_group_35)
           "36"->imageView.setImageResource(R.drawable.ic_group_36)
            else->imageView.setImageResource(R.drawable.ic_no_result)

       }
    }
}