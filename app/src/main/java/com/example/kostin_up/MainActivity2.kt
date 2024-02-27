package com.example.kostin_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.kostin_up.databinding.ActivityMain2Binding
import com.example.kostin_up.databinding.PostCardBinding


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
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
        }
    }

}


fun toStringNumb(count:Int):String {
    return when(count){
        in 0..<1_000 -> count.toString()
        in 1000..<1_100-> "1K"
        in 1_100..<10_000 -> ((count/100).toFloat()/10).toString() + "K"
        in 10_000..<1_000_000 -> (count/1_000).toString() + "K"
        in 1_000_000..<1_100_000 -> "1M"
        in 1_100_000..<10_000_000 -> ((count/100_000).toFloat()/10).toString() + "M"
        in 10_000_000..<1_000_000_000 -> (count/1_000_000).toString() + "M"
        else -> "более млрд"
    }

}














