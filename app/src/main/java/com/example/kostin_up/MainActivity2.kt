package com.example.kostin_up

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import com.example.kostin_up.databinding.ActivityMain2Binding
import com.example.kostin_up.databinding.LayoutPostAddEditBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar


class MainActivity2 : AppCompatActivity(),Listener {
    private val viewModel: PostViewModel by viewModels()
    private lateinit var binding:ActivityMain2Binding
    private lateinit var bindingAddEditBinding:LayoutPostAddEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        bindingAddEditBinding = LayoutPostAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostsAdapter (this)
        binding.recyclerView.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        binding.imageButtonAddPost.setOnClickListener {
            with(bindingAddEditBinding){
                setContentView(root)
                textViewContent.setText( "")
                textAuthor.setText("")
                textData.text = ""
                imageButtonCancel.setOnClickListener {
                    setContentView(binding.root)
                }
                imageButtonSavePost.setOnClickListener {
                    viewModel.add(textAuthor.text.toString(),textViewContent.text.toString())
                    setContentView(binding.root)
                }
            }
        }
        intent?.let {
            if(it.action != Intent.ACTION_SEND)
                return@let

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                Snackbar.make(binding.root, "Ошибка! Пустое значение!", BaseTransientBottomBar.LENGTH_INDEFINITE).setAction(android.R.string.ok) {
                    finish()
                }
                    .show()
                return@let
            }
        }

    }


    override fun onClickLike(post: Post) {
        viewModel.likedById(post.id)
    }

    override fun onClickShare(post: Post) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, post.content)
            type = "text/plain"

        }
        val shareIntent = Intent.createChooser(intent, "Выберите приложение")
        startActivity(shareIntent)
    }


    override fun onClickMore(view: View, post: Post) {
        val popupMenu = PopupMenu(this,view)

        popupMenu.inflate(R.menu.menu_post)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId)
            {
                R.id.menu_post_item_delete -> viewModel.removeById(post.id)
                R.id.menu_post_item_edit ->{
                    with(bindingAddEditBinding){
                        setContentView(root)
                        textViewContent.setText( post.content)
                        textAuthor.setText( post.author)
                        textData.text = post.data
                        imageButtonCancel.setOnClickListener {
                            setContentView(binding.root)
                        }
                        imageButtonSavePost.setOnClickListener {
                            viewModel.changeById(post.id,textAuthor.text.toString(),textViewContent.text.toString())
                            setContentView(binding.root)
                        }
                    }
                }
            }
            true

        }

        popupMenu.show()

    }
}
















