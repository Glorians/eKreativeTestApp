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
    private lateinit var videoAdapter: VideoListAdapter
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
//        initFields()
        initFunc()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        checkAuthorization()
        Log.d(TAG, "onResume")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        setAdapter()
        Log.d(TAG, "onViewCreated")
    }

    //Variable Initialization
    private fun initFields() {
        listVideosRV = binding.listVideosYT
    }

    //Functions Initialization
    private fun initFunc() {
        loadData()
    }

    //Check authorization user
    private fun checkAuthorization() {
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser == null) {
            navigateToAuthorizationFragment()
        }
    }

    //Set adapter in Recycler View
    private fun setAdapter() {
        videoAdapter = VideoListAdapter()
        listVideosRV.apply {
            adapter=  videoAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        videoAdapter.setOnClickListener { video ->
            navigateToDetailsVideoFragment(video.videoID.id)
        }
    }

    private fun loadData() {
        viewModel.initListVideos()
        viewModel.allVideos?.observe(viewLifecycleOwner, { resultListVideo ->
            val listVideos = mutableListOf<VideoYT>()
            resultListVideo.forEach {
                listVideos.add(it.toVideoYT())
            }
            videoAdapter.differ.submitList(listVideos)
        })
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