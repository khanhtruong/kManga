package com.example.kmanga.service.shared_preferences

sealed class SPKey(val value: String) {
    object FirebaseToken : SPKey("firebase_token")
}