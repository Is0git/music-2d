package com.is0.music2d.main

sealed class MainGraph {
    protected abstract val routeName: String
    protected abstract val routePattern: String

    object Home : MainGraph() {
        public override val routeName: String
            get() = "home"

        public override val routePattern: String
            get() = routeName
    }

    object AlbumDetails : MainGraph() {
        const val ALBUM_ID = "albumId"

        public override val routeName: String
            get() = String.format(
                routePattern,
                "{$ALBUM_ID}",
            )

        public override val routePattern: String
            get() = "album/%s"
    }
}