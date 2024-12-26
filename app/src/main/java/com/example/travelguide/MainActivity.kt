package com.example.travelguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.travelguide.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализируем Koin
        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }

        // Koin-ViewModels
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }
}
