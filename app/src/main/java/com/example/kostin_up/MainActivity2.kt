package com.example.kostin_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kostin_up.databinding.ActivityMain2Binding
<<<<<<< HEAD
import com.example.kostin_up.databinding.CardPostBinding
=======
import com.example.kostin_up.databinding.PostCardBinding
>>>>>>> f1e4589c5b63a25868f4119604bc63c735b96e35


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< HEAD
        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter {
            viewModel.likedById(it.id)
            }
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
=======
        val postViewModel: PostViewModel by viewModels()

        postViewModel.data.observe(this){posts->
            posts.map {post ->
                PostCardBinding.inflate(layoutInflater, binding.container, true).apply {
                    textAuthor.text = post.textAuthor
                    textData.text = post.textData
                    textViewContent.text = post.textViewContent
                    imageLike.setImageResource(
                        if (post.isLike) R.drawable.likeonn else R.drawable.likeofff
                    )
                    imageLike.setOnClickListener {
                        postViewModel.likeById(post.id)
                    }
                }.root
            }
>>>>>>> f1e4589c5b63a25868f4119604bc63c735b96e35
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
















