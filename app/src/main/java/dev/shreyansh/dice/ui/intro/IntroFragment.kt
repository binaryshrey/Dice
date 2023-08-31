package dev.shreyansh.dice.ui.intro

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.dice.R
import dev.shreyansh.dice.databinding.FragmentIntroBinding
import dev.shreyansh.dice.utils.NetworkConnection
import dev.shreyansh.dice.viewModel.DiceViewModel
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class IntroFragment : Fragment() {

    private var isConnected = false
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding : FragmentIntroBinding
    private val viewModel: DiceViewModel by activityViewModels()
    private var getIsGameModeSelectionComplete : Boolean = false
    private var gameMode : String = ""

    @Inject lateinit var networkConnection: NetworkConnection

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        (activity as AppCompatActivity).supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity().applicationContext, gso)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)

        Timber.i("IntroFragment inflated")

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        networkConnection.observe(viewLifecycleOwner, Observer { connected ->
            isConnected = connected
        })

        viewModel.loginComplete.observe(viewLifecycleOwner, Observer { hasLoggedIn ->
            if (hasLoggedIn) {
                if(isConnected){
                    binding.loadingTicketProgress.visibility = View.VISIBLE
                    signInFlow()
                }
                else{
                    Toast.makeText(context,"NO INTERNET CONNECTION!", Toast.LENGTH_SHORT).show()
                }
            }
        })
        return binding.root
    }
    private fun signInFlow() {
        val signInIntent = googleSignInClient.signInIntent
        startForResult.launch(signInIntent)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount? = task.result
                    if (account != null) {
                        firebaseAuthWithGoogle(account)
                    }
                } else {
                    viewModel.onLoginCancel()
                    Toast.makeText(context, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    Timber.e( task.exception.toString())
                }
            }
            if (result.resultCode == Activity.RESULT_CANCELED) {
                viewModel.onLoginCancel()
                binding.loadingTicketProgress.visibility = View.GONE
            }
        }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                binding.loadingTicketProgress.visibility = View.GONE
                findNavController().navigate(R.id.action_introFragment_to_prefsFragment)
                viewModel.onLoginComplete()
                Timber.i( "Login Success!")
            } else {
                viewModel.onLoginCancel()
                binding.loadingTicketProgress.visibility = View.GONE
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                Timber.e( it.exception.toString())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            viewModel.gameMode.observe(viewLifecycleOwner, Observer {
                it?.let{
                    when(it){
                        "one" -> findNavController().navigate(R.id.action_introFragment_to_boardOneFragment)
                        "two" -> findNavController().navigate(R.id.action_introFragment_to_boardTwoFragment)
                        "three" -> findNavController().navigate(R.id.action_introFragment_to_boardThreeFragment)
                        "four" -> findNavController().navigate(R.id.action_introFragment_to_boardFourFragment)
                        "None" -> findNavController().navigate(R.id.action_introFragment_to_prefsFragment)
                    }
                }
            })
        }
    }
}