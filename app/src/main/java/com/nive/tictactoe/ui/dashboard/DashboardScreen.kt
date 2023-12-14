package com.niveditha.tictactoe.ui.dashboard

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.niveditha.tictactoe.R
import com.niveditha.tictactoe.data.GameMode
import com.niveditha.tictactoe.data.TicTacToeDestination
import com.niveditha.tictactoe.uicomponent.GameModeSelector

@Composable
fun DashboardScreen(
	navController: NavController,
	viewModel: DashboardViewModel
) {
	
	val context = LocalContext.current
	
	val playButtonEnabled = remember(viewModel.bluetoothAvailable, viewModel.selectedGameMode) {
//		if (!viewModel.bluetoothAvailable && viewModel.selectedGameMode == GameMode.PvPBluetooth) {
//			return@remember false
//		}
		
		return@remember true
	}
	
	LaunchedEffect(context) {
		viewModel.checkBluetooth(context)
	}
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = Modifier
			.fillMaxSize()
	) {
		Image(
			painter = painterResource(id = R.drawable.ic_app_icon),
			contentDescription = null,
			modifier = Modifier
				.size(96.dp)
				.clip(MaterialTheme.shapes.large)
		)
		
		Spacer(modifier = Modifier.padding(8.dp))
		
		GameModeSelector(
			gameModes = GameMode.values(),
			selectedGameMode = viewModel.selectedGameMode,
			onGameModeChanged = viewModel::updateGameMode,
			modifier = Modifier
				.fillMaxWidth(0.6f)
		)
		
		Spacer(modifier = Modifier.padding(8.dp))
		
		OutlinedButton(
			enabled = playButtonEnabled,
			onClick = {
				navController.navigate(
					TicTacToeDestination.Game.Home.createRoute(
						gameMode = viewModel.selectedGameMode
					)
				)
			}
		) {
			Text("Play")
		}
		
		Spacer(modifier = Modifier.padding(16.dp))
		
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
//			IconButton(
//				onClick = {
//					navController.navigate(TicTacToeDestination.Score.Home.route)
//				}
//			) {
//				Icon(
//					painter = painterResource(id = R.drawable.ic_award),
//					contentDescription = null
//				)
//			}
			
			IconButton(
				onClick = {
					context.startActivity(
						Intent(Intent.ACTION_VIEW).apply {
							flags = Intent.FLAG_ACTIVITY_NEW_TASK
							data = Uri.parse("https://sites.google.com/view/nivedithagouda/home")
						}
					)
				}
			) {
				Icon(
					painter = painterResource(id = R.drawable.dev),
					contentDescription = null
				)
			}
		}
		
		Spacer(modifier = Modifier.padding(16.dp))
		
		OpenSourceText()
	}
}

@Composable
fun OpenSourceText() {
	
	val context = LocalContext.current
	
	val openSourceProjectMsg = buildAnnotatedString {
		withStyle(
			style = LocalTextStyle.current.copy(
				textAlign = TextAlign.Center,
				fontWeight = FontWeight.Light
			).toSpanStyle()
		) {
			append("")
			
			pushStringAnnotation(tag = "By Niveditha", annotation = "https://sites.google.com/view/nivedithagouda/home")

			withStyle(
				style = SpanStyle(
					color = MaterialTheme.colorScheme.primary,
					fontWeight = FontWeight.Medium
				)
			) {
				append(" ")
			}
			
			pop()
			
			append(" ")
		}
	}
	
	Text(
		text = "XO GAME \n     developed by Niveditha",
		style = MaterialTheme.typography.titleLarge.copy(
			fontWeight = FontWeight.Light
		)
	)
	
	Spacer(modifier = Modifier.padding(8.dp))
	
	ClickableText(
		text = openSourceProjectMsg,
		style = LocalTextStyle.current.copy(
			textAlign = TextAlign.Center
		),
		onClick = { offset ->
			openSourceProjectMsg.getStringAnnotations(
				tag = "github",
				start = offset,
				end = offset
			).firstOrNull()?.let { _ ->
				context.startActivity(
					Intent(Intent.ACTION_VIEW).apply {
						flags = Intent.FLAG_ACTIVITY_NEW_TASK
						data = Uri.parse("https://sites.google.com/view/nivedithagouda/home")
					}
				)
			}
		},
		modifier = Modifier
			.fillMaxWidth(0.7f)
	)
}
