package com.example.kostin_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
<<<<<<< HEAD
    fun get(): LiveData<Post>

    fun share()
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = listOf(
        Post(
            id = 1,
            textAuthor = "Костин УП",
            textViewContent = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
            textData = "Только что",
            amountShare = 0,
            imageLike = false,
            amountLike = 0
        ),
        Post(
            id = 2,
            textAuthor = "Костин УП",
            textViewContent = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
            textData = "Только что",
            amountShare = 0,
            imageLike = false,
=======
    fun share()
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Int)
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
        id = 1,
        textAuthor = "Костин УП",
        textViewContent = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
        textData = "Только что",
        amountShare = 0,
        isLike = false,
        amountLike = 0
        ),
        Post(
            id = 2,
            textAuthor = "БТПИТ",
            textViewContent = "Борисоглебский техникум промышленных и информационных технологий.",
            textData = "Только что",
            amountShare = 0,
            isLike = false,
>>>>>>> f1e4589c5b63a25868f4119604bc63c735b96e35
            amountLike = 0
        ),
    )

<<<<<<< HEAD
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> {
        TODO("Not yet implemented")
    }

    override fun share() {
        TODO("Not yet implemented")
    }

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
       post = post.map {
           if(it.id != id) it else it.copy(imageLike = !it.imageLike)
       }
        data.value = post
    }
   /* override fun like() {
        if (post.imageLike) post.amountLike-- else post.amountLike++
        post = post.copy(imageLike = !post.imageLike)

        data.value = post
    }
    override fun share() {
         post.amountShare++
        data.value = post
    }*/
=======
    private val data = MutableLiveData(posts)
    override fun likeById(id: Int) {
        if (posts[id].isLike == false){
        posts[id].isLike = true
        posts[id].amountLike++
        }
        else {
            posts[id].isLike = false
            posts[id].amountLike--
        }
        data.value = posts
    }
    override fun share() {
        // posts[0]
        data.value = posts
    }

    override fun getAll(): LiveData<List<Post>> = data
>>>>>>> f1e4589c5b63a25868f4119604bc63c735b96e35
}



class PostViewModel: ViewModel(){
    private val repository:PostRepository =  PostRepositoryInMemoryImpl()
    val data = repository.getAll()
<<<<<<< HEAD
    fun likedById(id: Long) = repository.likeById(id)

=======
    fun likeById(id: Int) = repository.likeById(id)
>>>>>>> f1e4589c5b63a25868f4119604bc63c735b96e35
    fun share() = repository.share()
}