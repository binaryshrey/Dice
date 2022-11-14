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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_intro,container,false)

        Timber.i("IntroFragment inflated")

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.eventGameStart.observe(viewLifecycleOwner, Observer { hasStarted ->
            if(hasStarted){
                findNavController().navigate(R.id.action_introFragment_to_gameFragment)
                viewModel.onGameStartComplete()
            }
        })
        return binding.root
    }
}