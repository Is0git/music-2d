package com.is0.music2d.utils.error.formatter

interface ErrorFormatter {
    fun formatError(throwable: Throwable): String
}