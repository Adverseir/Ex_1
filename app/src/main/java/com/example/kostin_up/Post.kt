package com.example.kostin_up

data class Post(
    val id: Long,
    val textAuthor: String,
    val textViewContent: String,
    var amountShare: Int,
    val textData: String,
    val imageLike: Boolean = false,
    var amountLike: Int,
)