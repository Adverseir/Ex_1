package com.example.kostin_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        textAuthor = "Костин УП",
        textViewContent = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
        textData = "Только что",
        amountShare = 0,
        imageLike = false,
        amountLike = 0

    )

    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like() {
        if (post.imageLike) post.amountLike-- else post.amountLike++
        post = post.copy(imageLike = !post.imageLike)

        data.value = post
    }
    override fun share() {
         post.amountShare++
        data.value = post
    }
}
class PostViewModel: ViewModel(){
    private val repository:PostRepository =  PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}