package com.example.yameritoxmlcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.example.yameritoxmlcompose.ui.fragments.LocalizationFragment
import com.example.yameritoxmlcompose.ui.fragments.LocalizationViewModel
import com.example.yameritoxmlcompose.ui.navigation.AppNavigation

class MainActivity : AppCompatActivity() {

    private val localizationViewModel : LocalizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hello = findViewById<ComposeView>(R.id.hello)
        hello.setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    // TODO: Navigation Drawer
                    AppNavigation(localizationViewModel)
                }
                
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LocalizationFragment.newInstance())
                .commitNow()
        }
    }
}