package com.example.kostin_up

data class Post(
    val id: Int,
    val textAuthor: String,
    val textViewContent: String,
    var amountShare: Int,
    val textData: String,
    var isLike: Boolean = false,
    var amountLike: Int,
)