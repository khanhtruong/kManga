package com.example.kmanga.domain.validator

import android.util.Patterns
import javax.inject.Inject

class ValidatorImpl @Inject constructor(): ValidatorService {
    override fun isValidLink(link: String): Boolean {
        return Patterns.WEB_URL.matcher(link).matches()
    }
}