package com.mvcproject.mvc.app

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.LocaleResolver
import java.util.*

@Controller
class LocaleChangeController(private val localeResolver: LocaleResolver) {
    @GetMapping("/lang")
    fun changeLocale(
        @RequestParam("lang") language: String,
        request: HttpServletRequest,
        response: HttpServletResponse,
        model: Model
    ): String {
        val locale = Locale(language)
        localeResolver.setLocale(request, response, locale)
        // Optionally, you can add logic to update the model with the new locale
        // model.addAttribute("locale", locale);
        return "redirect:" + request.getHeader("Referer") // Redirect back to the previous page
    }
}