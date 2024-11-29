package com.goncalo.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.goncalo.myapplication.presentation.property_detail.screen.PropertyDetailScreen
import com.goncalo.myapplication.presentation.property_list.screen.PropertyListScreen
import com.goncalo.myapplication.presentation.property_list.viewmodel.PropertyListViewModel
import com.goncalo.myapplication.presentation.ui.theme.HWAppChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<PropertyListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HWAppChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = Screens.PropertyList.route) {
                            composable(Screens.PropertyList.route) {
                                PropertyListScreen(navController = navController, viewModel = viewModel)
                            }

                            composable(route = Screens.PropertyDetails.route+"/{propertyId}", arguments = listOf(
                                navArgument("propertyId") {
                                    type = NavType.IntType
                                }
                            )) {
                                val propertyId = it.arguments?.getInt("propertyId")
                                propertyId?.let {
                                    PropertyDetailScreen(
                                        navController = navController,
                                        propertyId = propertyId,
                                        viewModel = hiltViewModel()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Screens(val route: String) {
    data object PropertyList: Screens("property_list")
    data object PropertyDetails: Screens("property_details")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HWAppChallengeTheme {

    }
}