package dev.shreyansh.dice.ui.game

import android.os.Bundle
import android.view.*
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
import dev.shreyansh.dice.databinding.FragmentBoardOneBinding
import dev.shreyansh.dice.viewModel.DiceViewModel


class BoardOneFragment : Fragment() {

    private lateinit var binding: FragmentBoardOneBinding
    private val viewModel: DiceViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        (activity as AppCompatActivity).supportActionBar?.show()
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(),R.color.black)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_one, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        viewModel.resetData()
        viewModel.result.observe(viewLifecycleOwner, Observer { value ->
            if (value != "") {
                animateDice(binding)
                animateDiceResult(binding)
            }
        })
        binding.rollButton.setOnClickListener {
            binding.confetti.visibility = View.VISIBLE
            binding.rollButton.isEnabled=false
            binding.rollButton.isClickable=false
            viewModel.rollBoardOne()
            binding.rollButton.postDelayed(Runnable {
                binding.rollButton.isEnabled=true
                binding.rollButton.isClickable=true
            } , 2000)
            binding.rollButton.postDelayed(Runnable {
                binding.confetti.visibility = View.INVISIBLE
            } , 2000)

        }

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
        findNavController().navigate(R.id.action_boardOneFragment_to_introFragment)
    }

    private fun animateDice(binding: FragmentBoardOneBinding) {
        binding.dice1ImageView.animate().apply {
            duration = 200
            rotationYBy(360f)
        }.withEndAction {
            binding.dice1ImageView.animate().apply {
                duration = 100
                rotationYBy(3600f)
            }
        }
    }

    private fun animateDiceResult(binding: FragmentBoardOneBinding?) {
        binding?.resultImageView?.animate()?.apply {
            duration = 200
            rotationYBy(360f)
        }?.withEndAction {
            binding.resultImageView.animate().apply {
                duration = 200
                rotationYBy(3600f)
            }
        }
    }
}