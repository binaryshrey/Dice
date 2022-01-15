@file:Suppress("RedundantNullableReturnType", "RedundantNullableReturnType",
    "RedundantNullableReturnType"
)

package dev.shreyansh.dice.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private lateinit var binding : FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about, container, false)
        return binding.root
    }

}