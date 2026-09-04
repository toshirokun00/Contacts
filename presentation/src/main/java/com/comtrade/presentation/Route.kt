package com.comtrade.presentation

import kotlinx.serialization.Serializable

interface Route

@Serializable
data object MainScreenRoute : Route
@Serializable
data class ContactDetailScreenRoute(
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val avatarUrl: String?,
) : Route