package com.example.kostin_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.kostin_up.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val postViewModel: PostViewModel by viewModels()
        postViewModel.data.observe(this){post->
            with(binding){
                textAuthor.text = post.textAuthor
                textShare.text = post.amountShare.toString()
                amountLike.text = toStringNumb(post.amountLike)
                imageLike.setImageResource(if (post.imageLike) R.drawable.likeonn else R.drawable.likeofff)
            }
        }
        binding.imageLike.setOnClickListener {
            postViewModel.like()
        }
        binding.imageShare.setOnClickListener {
            postViewModel.share()
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














