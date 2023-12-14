package com.niveditha.tictactoe.extension

import com.niveditha.tictactoe.data.GameMode

val GameMode.modeName: String
	get() = when(this) {
		GameMode.Computer -> "Computer"
		GameMode.PvP -> "PvP"
//		GameMode.PvPBluetooth -> "PvP Bluetooth"
	}
