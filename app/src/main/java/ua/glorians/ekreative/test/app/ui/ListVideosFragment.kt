package ua.glorians.ekreative.test.app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.databinding.ListVideosFragmentBinding
import ua.glorians.ekreative.test.app.utils.showSnack
import ua.glorians.ekreative.test.app.viewmodel.ListVideosViewModel

class ListVideosFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ListVideosFragmentBinding
    private val viewModel: ListVideosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListVideosFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        checkAuthorization()
        initFields()
        initFunc()
    }

    override fun onResume() {
        super.onResume()
        checkAuthorization()
    }

    private fun initFields() {

    }

    private fun initFunc() {

    }

    private fun checkAuthorization() {
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser == null) {
            navigateToAuthorizationFragment()
        }
    }

    private fun navigateToAuthorizationFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.navigationFromListVideosToAuthorization)
    }

    companion object {
        private const val TAG = "ListVideosFragment"
    }

}