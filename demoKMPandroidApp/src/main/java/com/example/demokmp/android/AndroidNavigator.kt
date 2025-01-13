package com.example.demokmp.android

import androidx.navigation.NavController
import com.example.demokmp.Navigator

class AndroidNavigator(private val navController: NavController) : Navigator {
    override fun navigateTo(destination: String) {
        navController.navigate(destination)
//        when (destination) {
//            "Home" -> navController.navigate(R.id.homeFragment)
//            "Details" -> navController.navigate(R.id.detailsFragment)
//        }
    }

    override fun goBack() {
        navController.popBackStack()
    }
}