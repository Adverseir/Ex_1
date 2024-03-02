package com.example.kostin_up

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val data: String,
    var amountLike: Int = 0,
    var amountShare: Int = 0,
    var amountView:Int = 0,
    val isLiked: Boolean = false,
    val link:String,

)