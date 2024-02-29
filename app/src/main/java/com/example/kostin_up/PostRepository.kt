package com.example.kostin_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
    fun share()
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun removeById(id: Long)
    fun save(post: Post)
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            id = 1,
            textAuthor = "Костин УП",
            textViewContent = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
            textData = "Только что",
            amountShare = 0,
            imageLike = false,
            amountLike = 0,
        ),
        Post(
            id = 2,
            textAuthor = "Костин УП",
            textViewContent = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
            textData = "Только что",
            amountShare = 0,
            imageLike = false,
            amountLike = 0,
        ),
    )
    fun nextId(post: List<Post>):Int
    {
        var id = 1
        post.forEach { it1 ->
            post.forEach {
                if (it.id.toInt() ==id) id = (it.id+1).toInt()
            }
        }
        return id
    }
    private val data = MutableLiveData(posts)


    override fun share() {
        TODO("Not yet implemented")
    }

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
       posts = posts.map {
           if(it.id != id.toInt()) it else it.copy(imageLike = !it.imageLike)
       }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter {it.id !=id.toInt()}
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0) {
            posts = listOf(post.copy(
                id = nextId(posts),
                textAuthor = "Костин Вячеслав",
                imageLike = false,
            )) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if ( it.id!= post.id) it else it.copy(textViewContent = post.textViewContent)
        }
        data.value = posts
    }
}

private val empty = Post(
    id = 0,
    textAuthor = "",
    textViewContent = "",
    textData = "",
    imageLike = false,
    amountLike = 0,
    amountShare = 0

)

class PostViewModel: ViewModel(){
    private val repository:PostRepository =  PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun nextId(post:List<Post>):Long {
        var id = 1
        post.forEach { it1 ->
            post.forEach {
                if (it.id.toInt() == id) id = (it.id + 1).toInt()
            }
        }
        return id.toLong()
    }
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun changeContent(textViewContent: String) {
        edited.value?.let {
            val text = textViewContent.trim()
            if (it.textViewContent == text) {
                return
            }
            edited.value = it.copy(textViewContent = text)
        }
    }

    fun likedById(id: Long) = repository.likeById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun share() = repository.share()
}