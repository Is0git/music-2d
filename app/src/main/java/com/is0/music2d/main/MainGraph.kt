package com.is0.music2d.main

import okhttp3.internal.format

sealed class MainScreen {
    abstract val routePattern: String
    abstract val routeName: String

    object Home : MainScreen() {
        override val routePattern: String
            get() = routeName

        override val routeName: String
            get() = "home"
    }

    object AlbumDetails : MainScreen() {
        const val ALBUM_ID: String = "albumId"

        override val routePattern: String
            get() = "album/%s"

        override val routeName: String
            get() = formatRoute("{$ALBUM_ID}")
    }

    object StorageDetails : MainScreen() {
        const val STORAGE_TYPE: String = "storageType"

        override val routePattern: String
            get() = "storage/details/%s"

        override val routeName: String
            get() = formatRoute("{$STORAGE_TYPE}")
    }
}

fun MainScreen.formatRoute(vararg args: Any): String = format(
    routePattern,
    *args,
)