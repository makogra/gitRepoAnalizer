package com.example.demokmp

interface Navigator {
    fun navigateTo(destination: String)
    fun goBack()
}

sealed class NavigationDestination {
    object Home : NavigationDestination()
    object Details : NavigationDestination()
}