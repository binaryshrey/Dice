package dev.shreyansh.dice.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentBoardFourBinding


class BoardFourFragment : Fragment() {

    private lateinit var binding : FragmentBoardFourBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_one, container, false)
        return binding.root
    }


}