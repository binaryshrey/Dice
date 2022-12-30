package dev.shreyansh.dice.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentIntroBinding
import dev.shreyansh.dice.viewModel.DiceViewModel
import timber.log.Timber


class IntroFragment : Fragment() {

    private lateinit var binding : FragmentIntroBinding
    private val viewModel: DiceViewModel by activityViewModels()
    private var getIsGameModeSelectionComplete : Boolean = false
    private var gameMode : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)

        Timber.i("IntroFragment inflated")

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getIsGameModeSelectionComplete.observe(viewLifecycleOwner, Observer { value ->
            getIsGameModeSelectionComplete = value
        })
        viewModel.readGameMode.observe(viewLifecycleOwner, Observer { value ->
            gameMode = value
        })

        viewModel.eventGameIntro.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished) {
                if(gameMode == "one" && getIsGameModeSelectionComplete){
                    findNavController().navigate(R.id.action_introFragment_to_boardOneFragment)
                }
                else if(gameMode == "two" && getIsGameModeSelectionComplete){
                    findNavController().navigate(R.id.action_introFragment_to_boardTwoFragment)
                }
                else if(gameMode == "three" && getIsGameModeSelectionComplete){
                    findNavController().navigate(R.id.action_introFragment_to_boardThreeFragment)
                }
                else if(gameMode == "four" && getIsGameModeSelectionComplete){
                    findNavController().navigate(R.id.action_introFragment_to_boardFourFragment)
                }
                else{
                    findNavController().navigate(R.id.action_introFragment_to_prefsFragment)
                }
                viewModel.onGameIntroComplete()
            }
        })
        return binding.root
    }
}