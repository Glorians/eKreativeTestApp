package ua.glorians.ekreative.test.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.databinding.ItemVideoYoutubeBinding

class VideoListAdapter : RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

    override fun getItemCount() = differ.currentList.size
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var binding: ItemVideoYoutubeBinding
    private var onItemClickListener: ((VideoYT) -> Unit)? = null

    inner class VideoViewHolder(val view: ItemVideoYoutubeBinding): RecyclerView.ViewHolder(view.root) {
        val title = view.titleItemVideo
        val image = view.thumbnailItemVideo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemVideoYoutubeBinding.inflate(layoutInflater, parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = differ.currentList[position]
        holder.title.text = video.snippet.title
        imageLoad(holder, video.snippet.thumbnails.defaultSize.url)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(video) }
        }
    }

    private fun imageLoad(holder: VideoViewHolder, url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.placeholder_thumbnail_video)
            .into(holder.image)
    }

    fun setOnClickListener(listener: (VideoYT) -> Unit) {
        onItemClickListener = listener
    }


    private val differCallback = object : DiffUtil.ItemCallback<VideoYT>() {
        override fun areItemsTheSame(oldItem: VideoYT, newItem: VideoYT): Boolean {
            return oldItem.videoID.id == newItem.videoID.id
        }

        override fun areContentsTheSame(oldItem: VideoYT, newItem: VideoYT): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


}

