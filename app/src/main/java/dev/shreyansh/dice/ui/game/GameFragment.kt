package dev.shreyansh.dice.ui.game

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentGameBinding
import dev.shreyansh.dice.viewModel.DiceViewModel
import timber.log.Timber

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val viewModel: DiceViewModel by activityViewModels()

    private val address = arrayOf("binaryshrey.dev@gmail.com")
    private val subject: String = "DICE : Bug Report"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        setHasOptionsMenu(true)
        Timber.i("GameFragment Inflated")

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutFragment -> findNavController().navigate(R.id.action_gameFragment_to_aboutFragment)
            R.id.rateUs -> Toast.makeText(context,"Rate us",Toast.LENGTH_SHORT).show()
            R.id.shareBugReport -> bugReport()
        }
        return super.onOptionsItemSelected(item)
    }

    fun bugReport() {
        startActivity(composeBugReport(address, subject))
    }

    private fun composeBugReport(addresses: Array<String>, subject: String): Intent {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        return intent
    }
}