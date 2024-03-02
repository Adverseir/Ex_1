package com.example.kostin_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Int)
    fun shareById(id:Int)
    fun removeById(id: Int)
    fun changeById(id: Int,author:String,content:String)
    fun add(author: String, content: String)
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1
    private var posts = listOf(
        Post(
            id = 1,
            author = "Пост УП",
            content = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
            data = "Только что",
            amountShare = 0,
            isLiked = false,
            amountLike = 0,
        ),
        Post(
            id = 2,
            author = "Пост 2",
            content = "Государственное бюджетное профессиональное образовательное учреждение Воронежской области «Борисоглебский техникум промышленных и информационных технологий».",
            data = "Только что",
            amountShare = 0,
            isLiked = false,
            amountLike = 0,
        ),
    )

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Int) {
       posts = posts.map {
           if(it.id != id) it else{

               it.copy(isLiked = !it.isLiked, amountLike = if (it.isLiked) it.amountLike-1 else it.amountLike+1)
           }
       }
        data.value = posts
    }
    override fun shareById(id: Int) {
        posts = posts.map {
            if(it.id != id) it else it.copy( amountShare = it.amountShare+1)
        }
        data.value = posts
    }
    override fun removeById(id: Int) {
        posts = posts.filter { it.id != id}
        data.value = posts
    }

    override fun changeById(id: Int, author: String, content: String) {
        posts = posts.map {
            if (it.id != id) it
            else it.copy(author = author, content = content)
        }
        data.value = posts

    }
    override fun add(author: String, content: String) {
        posts = listOf(
            Post(
                id = nextId(),
                author = author,
                content = content,
                data = Calendar.getInstance().time.toString().split("GMT")[0],
                amountLike = 0,
                amountShare = 0,
                amountView = 0,
            )
        )+posts

        data.value = posts
    }

    private fun nextId():Int{
        var id = 1
        posts.forEach{ _ ->
            posts.forEach{
                if (it.id==id) id=it.id+1
            }
        }
        return id
    }

}




class PostViewModel: ViewModel(){
    private val repository:PostRepository =  PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likedById(id: Int) = repository.likeById(id)
    fun removeById(id: Int) = repository.removeById(id)
    fun changeById(id: Int, author: String, content: String) = repository.changeById(id,author,content)
    fun add(author: String, content: String) = repository.add(author,content)
    fun shareById(id: Int) = repository.shareById(id)
}