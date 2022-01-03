package dev.shreyansh.dice.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentGameBinding
import timber.log.Timber
import kotlin.random.Random


class GameFragment : Fragment() {

    private lateinit var binding : FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game, container, false)

        Timber.i("GameFragment Inflated")

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

}