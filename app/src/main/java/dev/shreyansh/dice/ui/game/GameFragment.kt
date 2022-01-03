package dev.shreyansh.dice.ui.game

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        setHasOptionsMenu(true)
        Timber.i("GameFragment Inflated")

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.aboutFragment -> Toast.makeText(context,"About",Toast.LENGTH_SHORT).show()
            R.id.rateUs -> Toast.makeText(context,"Rate",Toast.LENGTH_SHORT).show()
            R.id.shareBugReport -> Toast.makeText(context,"Bug",Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}