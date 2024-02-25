package com.mvcproject.mvc.app.user

import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest

import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller

import org.springframework.web.bind.annotation.RequestMapping



@Slf4j
@Controller
class errorPageController {
    @RequestMapping("/error-page/404")
    fun error404(req: HttpServletRequest, resp: HttpServletResponse?): String {
        //log.info("errorPage 404")
        printErrorInfo(req)
        return "error-page/404"
    }

    @RequestMapping("/error-page/500")
    fun error500(req: HttpServletRequest, resp: HttpServletResponse?): String {
        //log.info("errorPage 500")
        printErrorInfo(req)
        return "error-page/500"
    }

    private fun printErrorInfo(req: HttpServletRequest) {
        //log.info("dispatchTypes= {}", req.dispatcherType)
    }
}