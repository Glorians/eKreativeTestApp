package ua.glorians.ekreative.test.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.databinding.AuthorizationFragmentBinding
import ua.glorians.ekreative.test.app.viewmodel.AuthorizationViewModel

class AuthorizationFragment : Fragment() {

    private lateinit var binding: AuthorizationFragmentBinding
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthorizationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFields()
        initFunc()
    }

    private fun initFields() {

    }

    private fun initFunc() {

    }

    private fun googleAuth() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity().applicationContext, gso)
    }


}