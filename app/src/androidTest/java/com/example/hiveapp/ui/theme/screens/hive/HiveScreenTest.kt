package com.example.hiveapp.ui.theme.screens.hive

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(AndroidJUnit4::class)
//class HiveScreenTest {
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun testHiveScreenContent() {
//        // Uruchomienie widoku
//        composeTestRule.setContent {
//            HiveScreen(
//                id = 1,
//                navigator = mockNavigator(),
//                resultNavigator = mockResultBackNavigator
//            )
//        }
//
//        // Sprawdzenie tytułu
//        composeTestRule.onNodeWithText("Ul", useUnmergedTree = true).assertExists()
////
////        // Sprawdzenie przycisku lokalizacji
//        composeTestRule.onNodeWithText("Dodaj lokalizacje", useUnmergedTree = true).assertExists()
////
////        // Sprawdzenie przycisku pogody
////        composeTestRule.onNodeWithText("Pokaż pogodę", useUnmergedTree = true).assertExists()
////
////        // Sprawdzenie ikony menu
////        composeTestRule.onNodeWithContentDescription("Menu").assertExists()
////
////        // Sprawdzenie wyświetlania modala
////        composeTestRule.onNodeWithText("Czy na pewno chcesz usunąć?", useUnmergedTree = true).assertDoesNotExist()
//    }
//
//    // Mock navigators
//    private fun mockNavigator(): DestinationsNavigator {
//        return object : DestinationsNavigator {
//            override fun clearBackStack(route: String): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun navigate(
//                route: String,
//                onlyIfResumed: Boolean,
//                navOptions: NavOptions?,
//                navigatorExtras: Navigator.Extras?
//            ) {
//                TODO("Not yet implemented")
//            }
//
//            override fun navigate(
//                route: String,
//                onlyIfResumed: Boolean,
//                builder: NavOptionsBuilder.() -> Unit
//            ) {}
//
//            override fun navigateUp(): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun popBackStack(): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun popBackStack(
//                route: String,
//                inclusive: Boolean,
//                saveState: Boolean
//            ): Boolean {
//                TODO("Not yet implemented")
//            }
//        }
//    }
//
//    private val mockResultBackNavigator = object : ResultBackNavigator<Boolean> {
//        override fun navigateBack(result: Boolean, onlyIfResumed: Boolean) {
//            TODO("Not yet implemented")
//        }
//
//        override fun navigateBack(onlyIfResumed: Boolean) {}
//        override fun setResult(result: Boolean) {
//            TODO("Not yet implemented")
//        }
//    }
//}