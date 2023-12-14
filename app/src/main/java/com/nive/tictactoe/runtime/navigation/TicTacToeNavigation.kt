package com.niveditha.tictactoe.runtime.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.niveditha.tictactoe.data.TicTacToeDestination
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.niveditha.tictactoe.runtime.navigation.DashboardAnimatedNavHost
import com.niveditha.tictactoe.runtime.navigation.GameAnimatedNavHost
import com.niveditha.tictactoe.runtime.navigation.ScoreAnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TicTacToeNavigation() {
	
	val navController = rememberAnimatedNavController()
	val systemUiController = rememberSystemUiController()
	
	SideEffect {
		systemUiController.setSystemBarsColor(
			color = Color.Transparent,
			darkIcons = true
		)
	}
	
	AnimatedNavHost(
		navController = navController,
		startDestination = TicTacToeDestination.Dashboard.Root.route,
		modifier = Modifier
			.fillMaxSize()
	) {
		DashboardAnimatedNavHost(navController)
		
		ScoreAnimatedNavHost(navController)
		
		GameAnimatedNavHost(navController)
	}
	
}
