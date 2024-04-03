package com.example.kmanga.domain.validator

interface ValidatorService {
    fun isValidLink(link: String): Boolean
}