package dev.shreyansh.dice.ui.game

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentBoardFourBinding
import dev.shreyansh.dice.databinding.FragmentBoardThreeBinding
import dev.shreyansh.dice.viewModel.DiceViewModel


class BoardFourFragment : Fragment() {

    private lateinit var binding : FragmentBoardFourBinding
    private val viewModel: DiceViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(),R.color.black)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_four, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        viewModel.resetData()

        viewModel.result.observe(viewLifecycleOwner, Observer { value ->
            if (value != "") {
                animateDice(binding)
            }
        })
        binding.rollButton.setOnClickListener {
            binding.rollButton.isEnabled=false
            binding.rollButton.isClickable=false
            viewModel.rollBoardFour()
            binding.rollButton.postDelayed(Runnable {
                binding.rollButton.isEnabled=true
                binding.rollButton.isClickable=true
            } , 210)

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())
        when(sp.getString("GAME_MODE_KEY","")){
            "one" -> findNavController().navigate(R.id.action_boardFourFragment_to_boardOneFragment)
            "two" -> findNavController().navigate(R.id.action_boardFourFragment_to_boardTwoFragment)
            "three" -> findNavController().navigate(R.id.action_boardFourFragment_to_boardThreeFragment)

        }
        when(sp.getString("THEME_KEY","")){
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
            R.id.aboutFragment -> findNavController().navigate(R.id.action_boardFourFragment_to_aboutFragment)
            R.id.settings -> findNavController().navigate(R.id.action_boardFourFragment_to_settingsFragment)
            R.id.logout -> signOutFlow()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOutFlow() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googlesSignInClient = GoogleSignIn.getClient(requireContext(),gso)
        googlesSignInClient.signOut()
        FirebaseAuth.getInstance().signOut()
        findNavController().navigate(R.id.action_boardFourFragment_to_introFragment)
        Log.d("BottomSheet", "Log Out successful!")
    }

    private fun animateDice(binding: FragmentBoardFourBinding) {
        binding.dice1ImageView.animate().apply {
            duration = 100
            rotationYBy(360f)
        }.withEndAction {
            binding.dice1ImageView.animate().apply {
                duration = 100
                rotationYBy(3600f)
            }
        }
        binding.dice2ImageView.animate().apply {
            duration = 100
            rotationYBy(360f)
        }.withEndAction {
            binding.dice1ImageView.animate().apply {
                duration = 100
                rotationYBy(3600f)
            }
        }
        binding.dice3ImageView.animate().apply {
            duration = 100
            rotationYBy(360f)
        }.withEndAction {
            binding.dice3ImageView.animate().apply {
                duration = 100
                rotationYBy(3600f)
            }
        }
        binding.dice4ImageView.animate().apply {
            duration = 100
            rotationYBy(360f)
        }.withEndAction {
            binding.dice4ImageView.animate().apply {
                duration = 100
                rotationYBy(3600f)
            }
        }
    }
}