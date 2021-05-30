package ua.glorians.ekreative.test.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.data.model.ListVideos
import ua.glorians.ekreative.test.app.data.network.RetrofitClient
import ua.glorians.ekreative.test.app.databinding.ListVideosFragmentBinding
import ua.glorians.ekreative.test.app.ui.adapters.VideoListAdapter
import ua.glorians.ekreative.test.app.utils.showSnack
import ua.glorians.ekreative.test.app.viewmodel.ListVideosViewModel

class ListVideosFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ListVideosFragmentBinding
    private lateinit var listVideosRV: RecyclerView
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

    //Variable Initialization
    private fun initFields() {
        listVideosRV = binding.listVideosYT
    }

    //Functions Initialization
    private fun initFunc() {
        loadVideos()
    }

    //Check authorization user
    private fun checkAuthorization() {
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser == null) {
            navigateToAuthorizationFragment()
        }
    }

    //Loading Videos Data
    private fun loadVideos() {
        viewModel.loadVideos()
        viewModel.listVideos.observe(this) { listVideo ->
            setAdapter(listVideo)
        }
    }

    //Set adapter in Recycler View
    private fun setAdapter(listVideo: ListVideos) {
        listVideosRV.adapter = VideoListAdapter(listVideo.listVideosYT)
        listVideosRV.layoutManager = LinearLayoutManager(context)
    }

    // Navigate to Authorization Fragment
    private fun navigateToAuthorizationFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.navigationFromListVideosToAuthorization)
    }

    companion object {
        private const val TAG = "ListVideosFragment"
    }

}