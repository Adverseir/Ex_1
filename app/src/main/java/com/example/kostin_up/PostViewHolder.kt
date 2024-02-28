package com.example.kostin_up

import androidx.recyclerview.widget.RecyclerView
import com.example.kostin_up.databinding.CardPostBinding

class PostViewHolder (
    private val binding: CardPostBinding,
    private val onClickListener: OnClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            textAuthor.text = post.textAuthor
            textData.text = post.textData
            textViewContent.text = post.textViewContent

            imageLike.setImageResource(
                if (post.imageLike) R.drawable.likeonn else R.drawable.likeofff
            )
            imageLike.setOnClickListener{
                onClickListener(post)
            }
        }
    }
}
