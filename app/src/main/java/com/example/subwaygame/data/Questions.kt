package com.example.subwaygame.data

data class Questions(
    val code: Int,
    val msg: String,
    val newslist: List<Newslist>
)

data class Newslist(
    val id: String,
    val quest: String,
    val result: String
)