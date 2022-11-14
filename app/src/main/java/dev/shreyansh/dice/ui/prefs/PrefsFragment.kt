package dev.shreyansh.dice.ui.prefs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentPrefsBinding


class PrefsFragment : Fragment() {

    private lateinit var binding: FragmentPrefsBinding
    private var dicePref : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_prefs, container, false)

        binding.cardOne.setOnClickListener {
            uncheckCards()
            binding.cardOne.isChecked = true
            dicePref = "one"
        }
        binding.cardTwo.setOnClickListener {
            uncheckCards()
            binding.cardTwo.isChecked = true
            dicePref = "two"
        }
        binding.cardThree.setOnClickListener {
            uncheckCards()
            binding.cardThree.isChecked = true
            dicePref = "three"
        }
        binding.cardFour.setOnClickListener {
            uncheckCards()
            binding.cardFour.isChecked = true
            dicePref = "four"
        }

        binding.playButton.setOnClickListener {
            if(dicePref.isEmpty())
                Toast.makeText(context,"Select your board",Toast.LENGTH_SHORT).show()
            else view?.findNavController()?.navigate(R.id.action_prefsFragment_to_gameFragment)
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