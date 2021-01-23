package com.example.dice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.dice.R
import com.example.dice.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.getStartedButton.setOnClickListener { view: View ->
            //Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_homeFragment_to_diceFragment)
        }
        return binding.root
    }


}