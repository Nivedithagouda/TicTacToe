package com.niveditha.tictactoe.extension

import com.niveditha.tictactoe.data.PointType
import com.niveditha.tictactoe.data.WinType

fun PointType.toWinType(): WinType {
	return when (this) {
		PointType.Empty -> WinType.None
		PointType.X -> WinType.X
		PointType.O -> WinType.O
	}
}