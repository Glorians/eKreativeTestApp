package ua.glorians.ekreative.test.app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.VideoApplication
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.databinding.ListVideosFragmentBinding
import ua.glorians.ekreative.test.app.ui.adapters.VideoListAdapter
import ua.glorians.ekreative.test.app.utils.showSnack
import ua.glorians.ekreative.test.app.viewmodel.ListVideosViewModel
import ua.glorians.ekreative.test.app.viewmodel.VideoViewModel
import ua.glorians.ekreative.test.app.viewmodel.VideoViewModelFactory

class ListVideosFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ListVideosFragmentBinding
    private lateinit var listVideosRV: RecyclerView
    private val viewModel: VideoViewModel by viewModels {
        VideoViewModelFactory((requireContext().applicationContext as VideoApplication).repository)
    }

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
        viewModel.initListVideos()
        viewModel.allVideos?.observe(this) { listVideoWithSAT ->
            val listVideoYT = mutableListOf<VideoYT>()
            listVideoWithSAT.forEach {
                listVideoYT.add(it.toVideoYT())
            }
            setAdapter(listVideoYT)
        }
    }

    //Set adapter in Recycler View
    private fun setAdapter(listVideo: List<VideoYT>) {
        listVideosRV.adapter = VideoListAdapter(
            listVideo,
            object : VideoListAdapter.CallbackVideoList {
                override fun onItemClicked(videoYT: VideoYT) {
                    navigateToDetailsVideoFragment(videoYT.videoID.id)
                }
            }
        )
        listVideosRV.layoutManager = LinearLayoutManager(context)
    }

    // Navigate to Authorization Fragment
    private fun navigateToAuthorizationFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.navigationFromListVideosToAuthorization)
    }

    private fun navigateToDetailsVideoFragment(videoID: String) {
        Navigation.findNavController(binding.root).navigate(
            ListVideosFragmentDirections.navigationToDetailsVideo(videoID)
        )
    }

    companion object {
        private const val TAG = "ListVideosFragment"
    }

    private data class ListVideosFragmentDirections(
        val videoID: String
    ) : NavDirections {
        override fun getActionId(): Int = R.id.navigationFromListVideosToDetailsVideo

        override fun getArguments(): Bundle {
            val bundle = Bundle()
            bundle.putString("videoID", this.videoID)
            return bundle
        }
        companion object {
            fun navigationToDetailsVideo(videoID: String): NavDirections = ListVideosFragmentDirections(videoID)
        }
    }

}