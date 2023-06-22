package com.zenzo.zenzo

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text

@Composable
fun ConsecutiveDaysTracker() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("com.zenzo.zenzo", Context.MODE_PRIVATE)
    val consecutiveDays = sharedPreferences.getInt("consecutiveDays", 0)

    Text(
        text = "Consecutive Days: $consecutiveDays",
        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
    )
}
