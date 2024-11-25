package com.fetch.item.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fetch.core.ui.theme.FetchTheme
import com.fetch.item.ui.component.ItemScreen

class ItemActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTheme {
                ItemScreen()
            }
        }
    }
}