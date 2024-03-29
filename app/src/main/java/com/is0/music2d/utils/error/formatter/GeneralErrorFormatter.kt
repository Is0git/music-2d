package com.is0.music2d.utils.error.formatter

import android.content.Context
import com.is0.music2d.R
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class GeneralErrorFormatter @Inject constructor(
    @ActivityContext private val context: Context,
) : ErrorFormatter {
    override fun formatError(throwable: Throwable): String = context.getString(R.string.error_unknown)
}