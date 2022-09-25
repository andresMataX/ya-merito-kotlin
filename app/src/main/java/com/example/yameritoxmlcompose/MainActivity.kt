package com.example.yameritoxmlcompose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.example.yameritoxmlcompose.ui.fragments.LocalizationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hello = findViewById<ComposeView>(R.id.hello)
        hello.setContent {
            MaterialTheme {
                Column {
                    Hello()
                }
                
            }
        }

        val buttonNavegar = findViewById<Button>(R.id.buttonNavegar)
        buttonNavegar.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
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