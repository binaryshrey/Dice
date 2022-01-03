package dev.shreyansh.dice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentGameBinding
import kotlin.random.Random


class GameFragment : Fragment() {

    private lateinit var binding : FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game, container, false)
        binding.rollButton.setOnClickListener { roll() }
        return binding.root
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

    private fun roll() {
        val randomIntOne : Int = Random.nextInt(6) + 1
        val randomIntTwo : Int = Random.nextInt(6) + 1
        binding.dice1ImageView.setImageResource(setImage(randomIntOne))
        binding.dice2ImageView.setImageResource(setImage(randomIntTwo))
        binding.resultTextView.setText("You Rolled : ${(randomIntOne+randomIntTwo).toString()}")

    }
}