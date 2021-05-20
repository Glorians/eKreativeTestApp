package ua.glorians.ekreative.test.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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


}