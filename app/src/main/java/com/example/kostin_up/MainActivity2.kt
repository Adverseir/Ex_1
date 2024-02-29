package com.example.kostin_up

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kostin_up.databinding.ActivityMain2Binding
import com.example.kostin_up.databinding.CardPostBinding


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            onLikeListener = {
                viewModel.likedById(it.id.toLong())
            },
            onRemoveListener = {
                viewModel.removeById(it.id.toLong())
            }
        )
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)

        }
        binding.buttonSave.setOnClickListener {
            with(binding.editText) {
                if (text.isNullOrBlank()){
                Toast.makeText(
                    this@MainActivity2,
                    "Content can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
    }
}
object AndroidUtils {
    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
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
















