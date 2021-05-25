package ua.glorians.ekreative.test.app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.databinding.AuthorizationFragmentBinding
import ua.glorians.ekreative.test.app.utils.showSnack
import ua.glorians.ekreative.test.app.viewmodel.AuthorizationViewModel

class AuthorizationFragment : Fragment() {

    private lateinit var binding: AuthorizationFragmentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var btnSignGoogle: MaterialButton
    private lateinit var btnSignFacebook: MaterialButton
    private lateinit var callbackManagerFacebook: CallbackManager
    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthorizationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
//        clearBackStack()
    }

    //Variable Initialization
    private fun initFields() {
        btnSignGoogle = binding.btnSignInGoogle
        btnSignFacebook = binding.btnSignInFacebook
        auth = FirebaseAuth.getInstance()
    }

    //Functions Initialization
    private fun initFunc() {
        createGoogleSignRequest()
        clickSignInGoogle()
        clickSignInFacebook()
    }

    //Click Button Sign in Google
    private fun clickSignInGoogle() {
        btnSignGoogle.setOnClickListener {
            singInGoogle()
        }
        btnSignGoogle.setOnLongClickListener {
            signOut()
            true
        }
    }

    //Click Button Sign in Facebook
    private fun clickSignInFacebook() {
        signInFacebook()
    }

    //Google Sign in Request
    private fun createGoogleSignRequest() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity().applicationContext, gso)
    }

    //Sign in Google
    private fun singInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }

    //Sign in Facebook
    private fun signInFacebook() {
        callbackManagerFacebook = CallbackManager.Factory.create()

        //Click Button
        btnSignFacebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(
                this,
                listOf("email", "public_profile")
            )
        }

        //Get Result
        LoginManager.getInstance().registerCallback(
            callbackManagerFacebook,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Log.d(TAG, "facebook onSuccess:$result")
                    firebaseAuthFacebook(result!!.accessToken)
                }
                override fun onCancel() {
                    Log.d(TAG, "facebook onCancel")
                }
                override fun onError(error: FacebookException?) {
                    Log.d(TAG, "onError", error)
                }
            }
        )
    }

    //Sign out
    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        showSnack(binding.root, "SignOut")
    }

    //Firebase Authorization witch Google
    private fun firebaseAuthGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth(credential)
    }

    //Firebase Authorization witch Facebook
    private fun firebaseAuthFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        Log.d(TAG, token.token)
        firebaseAuth(credential)
    }

    //Firebase Authorization
    private fun firebaseAuth(credential: AuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    auth.currentUser
                    if (auth.currentUser != null) {
                        navigateToListVideos()
                    }
                } else {
                    showSnack(binding.root, "Auth Firebase Error")
                }
            }
    }

    //Expecting result from authorization fragment
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Google Sign in
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.result
                firebaseAuthGoogle(account!!.idToken!!)
            } catch (e: ApiException) {
                Log.e(TAG, "Google sign in failed", e)
            }
        }
        //Facebook Sign in
        else {
            callbackManagerFacebook.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun navigateToListVideos() {
        Navigation.findNavController(binding.root).navigate(R.id.navigationFromAuthorizationToListVideos)
    }

    companion object {
        private const val TAG = "AuthorizationFragment"
        const val GOOGLE_SIGN_IN = 7
    }

}