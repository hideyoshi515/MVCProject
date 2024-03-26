package com.mvcproject.mvc.app

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.LocaleResolver
import java.util.*


@ControllerAdvice
class GlobalControllerAdvice {

    @Autowired
    private lateinit var messageSource: MessageSource;

    @ModelAttribute("sessionLocale")
    fun addSessionLocale(locale: Locale): String {
        return locale.language
    }
}