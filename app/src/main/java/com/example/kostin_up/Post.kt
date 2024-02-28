package com.example.kostin_up

data class Post(
    val id: Int,
    val textAuthor: String,
    val textViewContent: String,
    var amountShare: Int,
    val textData: String,
<<<<<<< HEAD
    val imageLike: Boolean = false,
    var amountLike: Int
=======
    var isLike: Boolean = false,
    var amountLike: Int,
>>>>>>> f1e4589c5b63a25868f4119604bc63c735b96e35
)