package dev.shreyansh.dice.ui.prefs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        return binding.root
    }

}