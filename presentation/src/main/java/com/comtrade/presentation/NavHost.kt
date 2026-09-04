package com.comtrade.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.comtrade.presentation.screen.ContactDetailScreen
import com.comtrade.presentation.screen.MainScreen
import com.comtrade.presentation.viewmodel.ContactsViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: ContactsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenRoute,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) }
    ) {
        composable<MainScreenRoute> {
            MainScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable<ContactDetailScreenRoute> { backStackEntry ->
            val contactDetail = backStackEntry.toRoute<ContactDetailScreenRoute>()
            ContactDetailScreen(
                email = contactDetail.email ?: "",
                firstName = contactDetail.firstName ?: "",
                lastName = contactDetail.lastName ?: "",
                avatarUrl = contactDetail.avatarUrl ?: ""
            )
        }

    }
}
