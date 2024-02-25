package com.mvcproject.mvc.config

import org.springframework.boot.web.server.ConfigurableWebServerFactory
import org.springframework.boot.web.server.ErrorPage
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component


@Component
abstract class WebServerCustomizer :WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    override fun customize(factory: ConfigurableWebServerFactory) {
        // 404 error
        val error404 = ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404")

        // 500 error
        val error500 = ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/")

        // runtime Exception 및 runtime Exception 의 자식 예외도 포함
        val exPage = ErrorPage(RuntimeException::class.java, "/error-page/500")
        factory.addErrorPages(error404, error500, exPage)
    }
}