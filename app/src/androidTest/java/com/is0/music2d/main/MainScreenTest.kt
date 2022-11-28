package com.is0.music2d.main

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun selectStorage_storageIsSelected() {
        val node = activityRule.onNode(hasText("STORAGE"))

        node.performClick()

        node.assertIsSelected()
    }
}