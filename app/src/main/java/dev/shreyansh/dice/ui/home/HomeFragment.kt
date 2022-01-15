@file:Suppress("UsePropertyAccessSyntax", "RedundantSamConstructor", "RedundantNullableReturnType")

package dev.shreyansh.dice.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentHomeBinding
import timber.log.Timber


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        Timber.i("HomeFragment inflated")

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.eventGameStart.observe(viewLifecycleOwner, Observer { hasStarted ->
            if(hasStarted){
                findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
                viewModel.onGameStartComplete()
            }
        })
        return binding.root
    }
}