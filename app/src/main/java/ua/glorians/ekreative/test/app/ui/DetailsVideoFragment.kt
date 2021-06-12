package ua.glorians.ekreative.test.app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.VideoApplication
import ua.glorians.ekreative.test.app.databinding.DetailVideoFragmentBinding
import ua.glorians.ekreative.test.app.viewmodel.DetailsVideoViewModel
import ua.glorians.ekreative.test.app.viewmodel.VideoViewModelFactory

class DetailsVideoFragment : Fragment() {

    private lateinit var binding: DetailVideoFragmentBinding
    private lateinit var thumbnail: ImageView
    private lateinit var title: MaterialTextView
    private lateinit var viewCount: MaterialTextView
    private lateinit var description: MaterialTextView
    private lateinit var likes: MaterialTextView
    private lateinit var favorites: MaterialTextView
    private lateinit var comments: MaterialTextView
    private lateinit var btnShare: FloatingActionButton
    private val args by navArgs<DetailsVideoFragmentArgs>()
    private val videoID by lazy { args.videoID }
    private lateinit var link: String
    private val viewModel: DetailsVideoViewModel by viewModels {
        VideoViewModelFactory((requireContext().applicationContext as VideoApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailVideoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {
        thumbnail = binding.thumbnailDetailsVideo
        title = binding.titleDetailsVideo
        viewCount = binding.viewCountDetailsVideo
        description = binding.descriptionDetailsVideo
        likes = binding.textLikeDetailsVideo
        favorites = binding.textFavoriteDetailsVideo
        comments = binding.textCommentDetailsVideo
        btnShare = binding.btnShareLinkDetailsVideo
        link = "https://www.youtube.com/watch?v=$videoID"
    }

    private fun initFunc() {
        bindData()
        clickShareButton()
    }

    private fun bindData() {
        Log.d(TAG, videoID)
        viewModel.initDetailsVideo(videoID)
        viewModel.video.observe(this) {
            if (it != null) {
                title.text = it.snippet.title
                viewCount.text = "${it.statistics.viewCount} views"
                imageLoad(it.snippet.thumbnails.highSize.url)
                description.text = it.snippet.description
                likes.text = "${it.statistics.likeCount} likes"
                favorites.text = "${it.statistics.favoriteCount} favorite"
                comments.text = "${it.statistics.commentCount} comments"
            }
        }
    }

    private fun clickShareButton() {
        btnShare.setOnClickListener {
            shareLink(link)
        }
    }

    private fun shareLink(src: String) {
        val shareIntent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.type = "text/plain"
            this.putExtra(Intent.EXTRA_TEXT, src)
        }
        startActivity(shareIntent)

    }

    private fun imageLoad(url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.placeholder_thumbnail_video)
            .into(thumbnail)
    }

    companion object {
        private const val TAG = "DetailsVideoFragment"
    }
}