package dev.shreyansh.dice.ui.prefs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentPrefsBinding
import dev.shreyansh.dice.viewModel.DiceViewModel
import timber.log.Timber

@AndroidEntryPoint
class PrefsFragment : Fragment() {

    private lateinit var binding: FragmentPrefsBinding
    private val viewModel : DiceViewModel by activityViewModels()
    private var dicePref : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventGameStart.observe(viewLifecycleOwner, Observer { hasStarted ->
            if(hasStarted){
                viewModel.setGameMode(dicePref)
                when(dicePref){
                    "one" -> findNavController().navigate(R.id.action_prefsFragment_to_boardOneFragment)
                    "two" -> findNavController().navigate(R.id.action_prefsFragment_to_boardTwoFragment)
                    "three" -> findNavController().navigate(R.id.action_prefsFragment_to_boardThreeFragment)
                    "four" -> findNavController().navigate(R.id.action_prefsFragment_to_boardFourFragment)
                }
                viewModel.onGameStartComplete()
            }
        })

        return binding.root
    }

    private fun uncheckCards(){
        binding.cardOne.isChecked = false
        binding.cardTwo.isChecked = false
        binding.cardThree.isChecked = false
        binding.cardFour.isChecked = false
    }

}