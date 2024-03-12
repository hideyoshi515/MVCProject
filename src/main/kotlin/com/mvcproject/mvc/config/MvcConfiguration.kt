package com.mvcproject.mvc.config

import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.Locale


@Configuration
@EnableCaching
class MvcConfiguration : WebMvcConfigurer {
    @Bean
    fun localeResolver(): LocaleResolver {
        val resolver = SessionLocaleResolver()
        resolver.setDefaultLocale(Locale.KOREA)
        return resolver
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val interceptor = LocaleChangeInterceptor()
        interceptor.paramName = "lang"
        return interceptor
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }

    @GetMapping("/redirected")
    fun redirected(): String {
        return "redirected"
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/")
            .setCachePeriod(3600)
    }

    @Bean
    fun cacheManager(): ConcurrentMapCacheManager {
        return ConcurrentMapCacheManager(
            "releasedate",
            "releasedateReverse",
            "asckor",
            "desckor",
            "ascjpn",
            "descjpn",
            "asceng",
            "desceng",
            "skills",
            "skillsTC",
            "skillsC",
            "selectone",
            "supportcard",
            "supportcardChar",
            "supportcardId",
            "selectIkusei"
        )
    }
}