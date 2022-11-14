package dev.shreyansh.dice.ui.prefs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentPrefsBinding


class PrefsFragment : Fragment() {

    private lateinit var binding: FragmentPrefsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_prefs, container, false)

        binding.cardOne.setOnClickListener {
            uncheckCards()
            binding.cardOne.isChecked = true
        }
        binding.cardTwo.setOnClickListener {
            uncheckCards()
            binding.cardTwo.isChecked = true
        }
        binding.cardThree.setOnClickListener {
            uncheckCards()
            binding.cardThree.isChecked = true
        }
        binding.cardFour.setOnClickListener {
            uncheckCards()
            binding.cardFour.isChecked = true
        }

        binding.playButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_prefsFragment_to_gameFragment)
        }
        return binding.root
    }

    private fun uncheckCards(){
        binding.cardOne.isChecked = false
        binding.cardTwo.isChecked = false
        binding.cardThree.isChecked = false
        binding.cardFour.isChecked = false
    }

}