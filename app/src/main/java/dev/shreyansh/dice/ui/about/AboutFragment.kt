package dev.shreyansh.dice.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentAboutBinding
import dev.shreyansh.dice.viewModel.DiceViewModel


@AndroidEntryPoint
class AboutFragment : Fragment() {

    private lateinit var binding : FragmentAboutBinding
    private val viewModel: DiceViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about, container, false)
        binding.rateAppCV.setOnClickListener {
            openWebURI(viewModel.appURI)
        }
        binding.bugCV.setOnClickListener {
            openWebURI(viewModel.issuesURI)
        }
        binding.devCV.setOnClickListener {
            openWebURI(viewModel.developerURI)
        }

        binding.supportAppCV.setOnClickListener {
            Toast.makeText(context,"Coming Soon!", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }
    private fun openWebURI(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

}