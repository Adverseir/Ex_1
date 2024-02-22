package com.example.kostin_up

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData

class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        var Button: Button = findViewById(R.id.button)
        var re: TextView = findViewById(R.id.textView5)
        var y = re.text.toString().toInt()
        y++
        re.text = y.toString()
        Button.setOnClickListener {

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        var textVie: TextView = findViewById(R.id.textView3)
        var s = textVie.text.toString().toInt()

        var count = 0
        var btnImg: ImageButton = findViewById(R.id.imageButton)
        btnImg.setOnClickListener {

            count++

            if (count === 1) {
                s++
                btnImg.setImageResource(R.drawable.likeonn)
            } else {
                count = 0
                s--
                btnImg.setImageResource(R.drawable.likeofff)
            }
            textVie.text = toStringNumb(s)

        }

        var mess: TextView = findViewById(R.id.textView4)
        var b = mess.text.toString().toInt()

        var count2 = 0

        var messim: ImageButton = findViewById(R.id.imageView11)
        messim.setOnClickListener {
            count2+=100
            mess.text = toStringNumb(count2)

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
    data class Post(
        val id: Long,
        val author: String,
        val content: String,
        val published: String,
        val likedByMe: Boolean,
    )

    interface PostRepository {
        fun get(): LiveData<Post>
        fun like()
    }

    class PostRepositoryInMemoryImpl : PostRepository {
        private var post = Post(
            id = 1,
            author = "БТПИТ",
            content = "аолдоадловадылвоадывлаодвылоад",
            published = "Только что",
            likedByMe = false

        )
        private val data = MutableLiveData(post)
        override fun get(): LiveData<Post> = data
        override fun like() {
            post = post.copy(likedByMe = !post.likedByMe)
            data.value = post
        }
    }











