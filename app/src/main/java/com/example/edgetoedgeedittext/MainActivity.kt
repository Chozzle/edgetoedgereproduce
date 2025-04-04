package com.example.edgetoedgeedittext

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        val composeView = findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            // Note that this reads 151 instead of 0 after config change
            Log.d("MainActivity", "Top systemBar insets: ${WindowInsets.systemBars.getTop(LocalDensity.current)}")

            Scaffold(topBar = {
                TopAppBar(title = { Text("TopAppBar Title") })
            }) { paddingValues ->
                Text("Some content", modifier = Modifier.padding(paddingValues))
            }
        }

        // To reproduce, run on Android API 34 and change theme from Light to Dark mode.
    }
}