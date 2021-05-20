package ua.glorians.ekreative.test.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ua.glorians.ekreative.test.app.databinding.ListVideosFragmentBinding
import ua.glorians.ekreative.test.app.viewmodel.ListVideosViewModel

class ListVideosFragment : Fragment() {

    private lateinit var binding: ListVideosFragmentBinding
    private val viewModel: ListVideosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListVideosFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initFields() {

    }

    private fun initFunc() {

    }

}