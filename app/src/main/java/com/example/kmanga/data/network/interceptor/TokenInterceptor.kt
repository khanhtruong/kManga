package com.example.kmanga.data.network.interceptor

import com.example.kmanga.service.LogoutManager
import com.example.kmanga.service.shared_preferences.SPKey
import com.example.kmanga.service.shared_preferences.SPService
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val spService: SPService,
    private val logoutManager: LogoutManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val rapidAPIKey = "932614fb98msh63a55b0fda32ebdp103ccejsnccfe0a9730dc"
        val request = chain.request()

        return when {
            rapidAPIKey.isEmpty() -> {
                chain.proceed(request)
            }
            else -> {
                val authRequest = chain.request()
                    .newBuilder()
                    .header(
                        RAPID_API_KEY_HEADER,
                        rapidAPIKey
                    )
                    .header(
                        RAPID_API_HOST_HEADER,
                        "myanimelist.p.rapidapi.com"
                    )
                    .build()

                // Become man in the middleware to check the response from server
                val response = chain.proceed(authRequest)

                if (response.code == 500 && response.body?.string()
                        ?.contains("Server Error Token expired") == true
                ) {

                    /**
                     * Refresh Token if can
                     **/

                    return chain.proceed(authRequest)
                }

                response
            }
        }
    }

    companion object {
        const val RAPID_API_KEY_HEADER = "X-RapidAPI-Key"
        const val RAPID_API_HOST_HEADER = "X-RapidAPI-Host"
    }
}