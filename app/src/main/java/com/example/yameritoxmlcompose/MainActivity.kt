package com.example.yameritoxmlcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.platform.ComposeView
import com.example.yameritoxmlcompose.ui.fragments.LocalizationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hello = findViewById<ComposeView>(R.id.hello)
        hello.setContent {
            Hello()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LocalizationFragment.newInstance())
                .commitNow()
        }
    }
}

@Composable
fun Hello() {
    Text(text = "El pepe")
}