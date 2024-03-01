package com.example.kostin_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import com.example.kostin_up.databinding.ActivityMain2Binding
import com.example.kostin_up.databinding.LayoutPostAddEditBinding


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

    }

    override fun onClickLike(post: Post) {
        viewModel.likedById(post.id)
    }

    override fun onClickShare(post: Post) {
        viewModel.shareById(post.id)

    }

    override fun onClickMore(view: View, post: Post) {
        val popupMenu = PopupMenu(this,view) //Объявление объекта меню

        popupMenu.inflate(R.menu.menu_post) //Указываю на каком лайоуте она будет показываться

        popupMenu.setOnMenuItemClickListener { //Слушатель нажиманий на итемы
            when(it.itemId)
            {
                R.id.menu_post_item_delete -> viewModel.removeById(post.id) //Удаление
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
            true //Просто хз, ноу комент

        }

        popupMenu.show() // Показываю менюшку

    }
}
















