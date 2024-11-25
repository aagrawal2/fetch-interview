package com.fetch.hire

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.fetch.item.ui.ItemActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val intent = Intent(this, ItemActivity::class.java)
        startActivity(intent)
        finish()
    }
}

