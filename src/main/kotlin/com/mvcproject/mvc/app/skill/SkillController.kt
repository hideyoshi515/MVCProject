package com.mvcproject.mvc.app.user

import com.mvcproject.mvc.domain.repository.SkillRepository
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
class SkillController(private val skillRepository: SkillRepository) {
    @Autowired
    lateinit var ms: MessageSource

    @RequestMapping("/skill","/skill/list")
    fun getToIndex(model: Model): String {
        val skill = skillRepository.showSkillByTypeCode()
        model.addAttribute("skills", skill)
        return "/skill/list/skilllist.html"
    }
/*
    @RequestMapping("/uma/list/{orderby}")
    fun getToOrderedIndex(model: Model, @PathVariable("orderby", required = false) orderby: String): String {
        when {
            orderby.equals("asckor", false) -> {
                // "kor" で昇順にソート
                model.addAttribute("nowsort", ms.getMessage("o_asckor", null, LocaleContextHolder.getLocale()))
                val umamusume = umamusumeRepository.showUmamusumeAscKor()
                model.addAttribute("umamusumes", umamusume)
            }
            orderby.equals("desckor", false) -> {
                // "kor" で降順にソート
                model.addAttribute("nowsort", ms.getMessage("o_desckor", null, LocaleContextHolder.getLocale()))
                val umamusume = umamusumeRepository.showUmamusumeDescKor()
                model.addAttribute("umamusumes", umamusume)
            }
            orderby.equals("ascjpn", false) -> {
                // "jpn" で昇順にソート
                model.addAttribute("nowsort", ms.getMessage("o_ascjpn", null, LocaleContextHolder.getLocale()))
                val umamusume = umamusumeRepository.showUmamusumeAscJpn()
                model.addAttribute("umamusumes", umamusume)
            }
            orderby.equals("descjpn", false) -> {
                // "jpn" で降順にソート
                model.addAttribute("nowsort", ms.getMessage("o_descjpn", null, LocaleContextHolder.getLocale()))
                val umamusume = umamusumeRepository.showUmamusumeDescJpn()
                model.addAttribute("umamusumes", umamusume)
            }
            orderby.equals("asceng", false) -> {
                // "eng" で昇順にソート
                model.addAttribute("nowsort", ms.getMessage("o_asceng", null, LocaleContextHolder.getLocale()))
                val umamusume = umamusumeRepository.showUmamusumeAscEng()
                model.addAttribute("umamusumes", umamusume)
            }
            orderby.equals("desceng", false) -> {
                // "eng" で降順にソート
                model.addAttribute("nowsort", ms.getMessage("o_desceng", null, LocaleContextHolder.getLocale()))
                val umamusume = umamusumeRepository.showUmamusumeDescEng()
                model.addAttribute("umamusumes", umamusume)
            }
            orderby.equals("release_r", false) -> {
                // リリース順に逆順にソート
                model.addAttribute("nowsort", ms.getMessage("o_release_r", null, LocaleContextHolder.getLocale()))
                val umamusume = umamusumeRepository.showUmamusumeReverse()
                model.addAttribute("umamusumes", umamusume)
            }
            else -> {
                // デフォルトはリリース順にソート
                model.addAttribute("nowsort", ms.getMessage("o_release", null, LocaleContextHolder.getLocale()))
                val umamusume = umamusumeRepository.showUmamusume()
                model.addAttribute("umamusumes", umamusume)
            }
        }
        return "/uma/list/umalist.html"
    }*/
}
