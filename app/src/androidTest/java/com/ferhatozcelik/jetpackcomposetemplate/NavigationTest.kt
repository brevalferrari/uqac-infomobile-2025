package com.ferhatozcelik.jetpackcomposetemplate

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ferhatozcelik.jetpackcomposetemplate.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `app should start by the home screen`() {
        composeTestRule.onNodeWithText("(not poutine)").assertIsDisplayed()
    }

    @Test
    fun `clicking plus on main screen should get you to the create screen`() {
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("Créer une routine").assertIsDisplayed()
    }
}
