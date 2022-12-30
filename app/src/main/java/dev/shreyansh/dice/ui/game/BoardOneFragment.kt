package dev.shreyansh.dice.ui.game

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentBoardOneBinding
import dev.shreyansh.dice.viewModel.DiceViewModel


class BoardOneFragment : Fragment() {

    private lateinit var binding: FragmentBoardOneBinding
    private val viewModel: DiceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_one, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        viewModel.resetData()
        viewModel.result.observe(viewLifecycleOwner, Observer { value ->
            if (value != "") {
                animateDice(binding)
            }
        })

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())
        when (sp.getString("GAME_MODE_KEY", "")) {
            "two" -> findNavController().navigate(R.id.action_boardOneFragment_to_boardTwoFragment)
            "three" -> findNavController().navigate(R.id.action_boardOneFragment_to_boardThreeFragment)
            "four" -> findNavController().navigate(R.id.action_boardOneFragment_to_boardFourFragment)

        }
        when (sp.getString("THEME_KEY", "")) {
            "Light Mode" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark Mode" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "System Default" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutFragment -> findNavController().navigate(R.id.action_boardOneFragment_to_aboutFragment)
            R.id.settings -> findNavController().navigate(R.id.action_boardOneFragment_to_settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun animateDice(binding: FragmentBoardOneBinding) {
        binding.dice1ImageView.animate().apply {
            duration = 100
            rotationYBy(360f)
        }.withEndAction {
            binding.dice1ImageView.animate().apply {
                duration = 100
                rotationYBy(3600f)
            }
        }
    }

}