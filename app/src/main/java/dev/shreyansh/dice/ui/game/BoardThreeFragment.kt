package dev.shreyansh.dice.ui.game

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentBoardThreeBinding
import dev.shreyansh.dice.viewModel.DiceViewModel


class BoardThreeFragment : Fragment() {

    private lateinit var binding : FragmentBoardThreeBinding
    private val viewModel: DiceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_three, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutFragment -> findNavController().navigate(R.id.action_boardThreeFragment_to_aboutFragment)
            R.id.settings -> Toast.makeText(context,"Settings", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}