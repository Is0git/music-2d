package com.is0.music2d.utils.data.mock

object ImageMock {
    val image
        get() = "https://d17fnq9dkz9hgj.cloudfront.net/breed-uploads/2018/08/samoyed-detail.jpg?bust=1535566500&width=630"

    val randomImage get() = "https://picsum.photos/200/300"

    fun getRandomImageById(id: Int) = "https://picsum.photos/id/$id/200/300"
}