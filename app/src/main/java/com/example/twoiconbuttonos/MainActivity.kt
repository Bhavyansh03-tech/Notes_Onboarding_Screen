package com.example.twoiconbuttonos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.twoiconbuttonos.presentation.navigation.NavGraph
import com.example.twoiconbuttonos.presentation.viewModel.MainViewModel
import com.example.twoiconbuttonos.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize splash screen
        val splashScreen = installSplashScreen()

        // Use coroutine scope to handle the splash condition
        val splashScope = CoroutineScope(Dispatchers.Main)
        splashScope.launch {
            viewModel.splashCondition.collect { splashCondition ->
                splashScreen.setKeepOnScreenCondition { splashCondition }
            }
        }

        enableEdgeToEdge()
        setContent {
            AppTheme {
                val startDestination by viewModel.startDestination.collectAsState()
                val navController = rememberNavController()
                if (startDestination != null) {
                    NavGraph(navController = navController, startDestination = startDestination!!)
                }
            }
        }
    }
}