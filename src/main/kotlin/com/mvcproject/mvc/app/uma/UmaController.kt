package com.mvcproject.mvc.app.uma

import com.mvcproject.mvc.domain.repository.IkuseiRepository
import com.mvcproject.mvc.domain.repository.SupportCardRepository
import com.mvcproject.mvc.domain.repository.UmamusumeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.support.RequestContextUtils
import java.util.*

/**
 * ウマ娘に関連するリクエストを処理するコントローラー。
 *
 * @param umamusumeRepository ウマ娘に関するデータアクセスを提供するリポジトリ。
 */
@Controller
class UmaController(private val umamusumeRepository: UmamusumeRepository, private  val supportRepository: SupportCardRepository, private  val ikuseiReository: IkuseiRepository) {
    @Autowired
    lateinit var ms: MessageSource

    /**
     * "/uma/list" エンドポイントへの GET リクエストを処理します。
     *
     * @param model Spring MVC モデル。
     * @return レンダリングするビューの名前。"/uma/list/umalist.html" が返されます。
     */
    @RequestMapping("/uma","/uma/list")
    fun getToIndex(model: Model): String {
        val umamusume = umamusumeRepository.showUmamusume()
        model.addAttribute("umamusumes", umamusume)
        return "/uma/list/umalist.html"
    }

    @RequestMapping("/uma/{umamusume}")
    fun getToOrderedIndex(model: Model, @PathVariable("umamusume", required = false) umamusume_param: String): String {
        val umamusume = umamusumeRepository.selectUmamusume(umamusume_param)
        val support = supportRepository.showSupportCardByChar(umamusume_param)
        val ikusei = ikuseiReository.selectIkusei(umamusume_param)
        model.addAttribute("select", umamusume)
        model.addAttribute("support", support)
        model.addAttribute("ikuseilist", ikusei)
        return "/uma/index/umamusume.html"
    }
}
