package com.polendina.androidtasknewapp.utils

import java.lang.System.*

class Constants {
    companion object {
        val API_KEY = getenv("NEWS_API")
        val countries = listOf(
            "us", "ar", "at", "au", "be", "bg", "br", "ca", "lc", "hc", "nc", "oc", "uc", "zd", "ee",
            "gf", "rg", "bg", "rh", "kh", "ui", "di", "ei", "li", "ni", "tj", "pk", "rl", "tl", "vm",
            "am", "xm", "yn", "gn", "ln", "on", "zp", "hp", "lp", "tr", "lo", "rs", "ru", "sa", "se",
            "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "ae", "ve", "za"
        )
        const val BASE_URL = "https://newsapi.org"
    }
    enum class Categories(val title: String) {
        BUSINESS(title = "business"),
        ENTERTAINMENT(title = "entertainment"),
        GENERAL(title = "general"),
        HEALTH(title = "health"),
        SCIENCE(title = "science"),
        SPORTS(title = "sports"),
        TECHNOLOGY(title = "technology")
    }
    enum class Languages(val code: String) {
        ENGLISH(code = "en"),
        GERMAN(code = "de"),
        ARABIC(code = "ar"),
        FRENCH(code = "fr"),
        ITALIAN(code = "it"),
        RUSSIAN(code = "rs")
    }
}