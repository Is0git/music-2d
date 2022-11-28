package com.is0.music2d.main.home.details.storage.utils.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class StorageSaveButtonComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenNotSaved_onSaveClick_showsIsSaved() {
        composeTestRule.setContent {
            AppTheme {
                val isSaved = remember {
                    mutableStateOf(false)
                }

                StorageSaveButtonComponent(
                    isSaved = isSaved.value,
                    onSaveClick = { isSaved.value = !isSaved.value },
                    storageType = SongStorageType.FILESYSTEM,
                )
            }
        }

        val node = composeTestRule.onNode(SemanticsMatcher.expectValue(isSongSavedKey, false))

        node.performClick()

        composeTestRule.onNode(SemanticsMatcher.expectValue(isSongSavedKey, true)).assertExists()
    }
}