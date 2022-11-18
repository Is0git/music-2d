package com.is0.music2d.music.use_case

import android.content.Context
import com.is0.music2d.utils.database.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckDatabaseExistsUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun exists(): Boolean {
        return try {
            File(context.getDatabasePath(AppDatabase.DATABASE_NAME).absolutePath).exists()
        } catch (error: Exception) {
            Timber.e(error)
            false
        }
    }
}