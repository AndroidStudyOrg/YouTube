package org.shop.youtube.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.shop.youtube.R
import org.shop.youtube.data.VideoItem
import org.shop.youtube.databinding.ItemVideoBinding

class VideoAdapter(private val context: Context) :
    ListAdapter<VideoItem, VideoAdapter.VideoViewHolder>(diffUtil) {
    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VideoItem) {
            binding.titleTextView.text = item.title
            binding.subTitleTextView.text = context.getString(
                R.string.sub_title_video_info,
                item.channelName,
                item.viewCount,
                item.dateText
            )

            Glide.with(binding.videoThumbnailImageView).load(item.videoThumb)
                .into(binding.videoThumbnailImageView)

            Glide.with(binding.channelLogoImageView).load(item.channelThumb).circleCrop()
                .into(binding.channelLogoImageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<VideoItem>() {
            override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}