package com.mvcproject.mvc.app.supportcard

import com.mvcproject.mvc.domain.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.support.RequestContextUtils
import java.util.*

@Controller
class SupportController(
    private val supportRepository: SupportCardRepository,
    private val skillsetRepository: SkillSetRepository,
    private val supportcardeffectRepository: SupportCardEffectRepository
) {
    @Autowired
    lateinit var ms: MessageSource

    @RequestMapping("/support", "/support/list")
    fun getToIndex(model: Model): String {
        val support = supportRepository.showSupportCard()
        model.addAttribute("support", support)
        return "/support/list/supportlist.html"
    }

    @RequestMapping("/support/{card}")
    fun getToOrderedIndex(model: Model, @PathVariable("card", required = false) card_param: String): String {
        val support = supportRepository.showSupportCardById(card_param)
        var effects = supportcardeffectRepository.showSupportCardEffectByCardId(card_param)
        model.addAttribute("support", support)
        model.addAttribute("effects", effects)
        return "/support/index/supportcard.html"
    }
}
