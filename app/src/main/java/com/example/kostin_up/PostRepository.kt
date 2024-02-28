package com.example.kostin_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
    fun get(): LiveData<Post>

    fun share()
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun removeById(id: Long)
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
            amountLike = 0,
            removeById = 12
        ),
        Post(
            id = 2,
            textAuthor = "Костин УП",
            textViewContent = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
            textData = "Только что",
            amountShare = 0,
            imageLike = false,
            amountLike = 0,
            removeById = 23
        ),
    )

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
           if(it.id != id.toInt()) it else it.copy(imageLike = !it.imageLike)
       }
        data.value = post
    }

    override fun removeById(id: Long) {
        post = post.filter { it.id != id.toInt()}
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
}



class PostViewModel: ViewModel(){
    private val repository:PostRepository =  PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likedById(id: Long) = repository.likeById(id)
    fun removeById(id: Long) = repository.removeById(id)

    fun share() = repository.share()
}