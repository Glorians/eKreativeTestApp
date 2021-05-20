package ua.glorians.ekreative.test.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.databinding.DetailVideoFragmentBinding
import ua.glorians.ekreative.test.app.viewmodel.DetailsVideoViewModel

class DetailsVideoFragment : Fragment() {

   private lateinit var binding: DetailVideoFragmentBinding
    private val viewModel: DetailsVideoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailVideoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }



}