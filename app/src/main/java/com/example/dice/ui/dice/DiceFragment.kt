package com.example.dice.ui.dice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dice.R
import com.example.dice.databinding.FragmentDiceBinding


class DiceFragment : Fragment() {

    private lateinit var binding: FragmentDiceBinding
    private lateinit var viewModel: DiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dice, container, false)

        viewModel = ViewModelProvider(this).get(DiceViewModel::class.java)

        binding.rollButton.setOnClickListener {
            viewModel.rollDice()
        }
        viewModel.dice.observe(viewLifecycleOwner, Observer { newValue ->
            binding.diceImageView.setImageResource(newValue)
        })
        return binding.root
    }
}