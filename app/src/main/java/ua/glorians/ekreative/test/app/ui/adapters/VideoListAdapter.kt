package ua.glorians.ekreative.test.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.glorians.ekreative.test.app.R
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.databinding.ItemVideoYoutubeBinding

class VideoListAdapter(private val listVideos: List<VideoYT>, private val callback: CallbackVideoList): RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    override fun getItemCount() = listVideos.size
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var binding: ItemVideoYoutubeBinding

    inner class ViewHolder(val view: ItemVideoYoutubeBinding): RecyclerView.ViewHolder(view.root){
        val title = view.titleItemVideo
        val image = view.thumbnailItemVideo

        fun setOnCallBack(videoYT: VideoYT) {
            view.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(videoYT)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemVideoYoutubeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = listVideos[position]
        holder.title.text = video.snippet.title
        imageLoad(holder, video.snippet.thumbnails.defaultSize.url)
        holder.setOnCallBack(video)
    }

    private fun imageLoad(holder: ViewHolder, url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.placeholder_thumbnail_video)
            .into(holder.image)
    }

    interface CallbackVideoList{
        fun onItemClicked(videoYT: VideoYT)
    }

}