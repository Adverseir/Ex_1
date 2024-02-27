package com.example.kostin_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
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
            amountLike = 0
        ),
    )

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
}
class PostViewModel: ViewModel(){
    private val repository:PostRepository =  PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Int) = repository.likeById(id)
    fun share() = repository.share()
}