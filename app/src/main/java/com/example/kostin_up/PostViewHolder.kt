package com.example.kostin_up

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kostin_up.databinding.CardPostBinding

interface Listener{
    fun onClickLike(post:Post)
    fun onClickShare(post:Post)
    fun onClickMore(view: View, post:Post)
}
class PostViewHolder (
    private val binding: CardPostBinding,
    private val listener: Listener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            textAuthor.text = post.author
            textData.text = post.data

            textViewContent.text = post.content

            textViewAmountLike.text = toStringNumb(post.amountLike)
            textViewAmountShare.text = toStringNumb(post.amountShare)
            textViewAmountView.text = toStringNumb(post.amountView)

            imageButtonLike.setImageResource(
                if (post.isLiked) R.drawable.ic_like_press else R.drawable.ic_like_unpress
            )
            imageButtonMore.setOnClickListener {
                listener.onClickMore(it, post)
            }
            imageButtonLike.setOnClickListener{
                listener.onClickLike(post)
            }
            imageButtonShare.setOnClickListener{
                listener.onClickShare(post)
            }
        }
    }
}
fun toStringNumb(count: Int): String {
    return when (count) {
        in 0..<1_000 -> count.toString()
        in 1000..<1_100 -> "1K"
        in 1_100..<10_000 -> ((count / 100).toFloat() / 10).toString() + "K"
        in 10_000..<1_000_000 -> (count / 1_000).toString() + "K"
        in 1_000_000..<1_100_000 -> "1M"
        in 1_100_000..<10_000_000 -> ((count / 100_000).toFloat() / 10).toString() + "M"
        in 10_000_000..<1_000_000_000 -> (count / 1_000_000).toString() + "M"
        else -> "более млрд"
    }
}