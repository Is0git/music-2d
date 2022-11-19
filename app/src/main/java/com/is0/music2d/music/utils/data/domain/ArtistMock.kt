package com.is0.music2d.music.utils.data.domain

import java.util.*

object ArtistMock {
    private val artists = listOf(
        getRandomArist(
            name = "Drake",
            artistImageUrl = "https://c-fa.cdn.smule.com/rs-s78/arr/02/68/e29c47d5-deba-40c8-b744-4f22f221bb7b.jpg",
        ),
        getRandomArist(
            name = "Lil Uzi",
            artistImageUrl = "https://firebase.soulectiontracklists.com/cdn/image/t_square_large/images/artists/lil-uzi-vert.jpg",
        ),
        getRandomArist(
            name = "Post Malone",
            artistImageUrl = "https://c-fa.cdn.smule.com/rs-s80/arr/08/66/7512f844-7d6b-4476-9b74-ee7edae61757.jpg",
        ),
        getRandomArist(
            name = "Travis Scott",
            artistImageUrl = "https://img.thedailybeast.com/image/upload/dpr_2.0/c_crop,h_1688,w_1688,x_931,y_0/c_limit,w_128/d_placeholder_euli9k,fl_lossy,q_auto/v1636402034/211108-Roundtree-travis-scott-spotify-tease_m61crm",
        ),
        getRandomArist(
            name = "Linkin Park",
            artistImageUrl = "https://m.media-amazon.com/images/I/41j+7Yu79ML._CR0,0,361,361_UX256.jpg",
        ),
        getRandomArist(
            name = "Coldplay",
            artistImageUrl = "https://styles.redditmedia.com/t5_2qmkl/styles/communityIcon_zmaw6ixwrgc71.png?width=256&s=d86a6aefce4832fe2dd37c9e854e2a87b8a57770",
        ),
        getRandomArist(
            name = "Sum 21",
            artistImageUrl = "https://external-preview.redd.it/PeZb70hoTBhPxSPN87Hv1eayYAzrk75qDgzg57Ffa4Y.jpg?auto=webp&s=b58e19c850b671f6b54a4dc25ec51d48cfee35b2",
        ),
    )

    val randomArtist
        get() = artists.random()

    fun getRandomArist(
        name: String,
        artistImageUrl: String,
    ): Artist = Artist(

        id = UUID.randomUUID().toString(),
        name = name,
        artistImageUrl = artistImageUrl
    )
}