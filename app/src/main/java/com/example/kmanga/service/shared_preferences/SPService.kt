package com.example.kmanga.service.shared_preferences

interface SPService {
    fun storeKeyPair(key: SPKey, value: String)
    fun retrieveValue(key: SPKey, default: String = ""): String

    fun storeObject(key: SPKey, obj: Any)
    fun <T> retrieveObject(key: SPKey, classOfT: Class<T>): T?

    fun clear()
}