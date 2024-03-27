package com.mvcproject.mvc.app.board

import com.mvcproject.mvc.domain.model.entity.RecruitF
import com.mvcproject.mvc.domain.repository.IkuseiRepository
import com.mvcproject.mvc.domain.repository.InshiRepository
import com.mvcproject.mvc.domain.repository.RecruitFRepository
import com.mvcproject.mvc.domain.repository.SupportCardRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.SessionAttribute
import org.springframework.web.servlet.support.RequestContextUtils
import java.util.*


@Controller
class FriendController(
    private val friendRepository: RecruitFRepository,
    private val ikuseiRepository: IkuseiRepository,
    private val supportRepository: SupportCardRepository,
    private val inshiRepository: InshiRepository
) {

    @GetMapping("/friend")
    fun showBoard(
        model: Model, @PageableDefault(size = 7) pageable: Pageable, request: HttpServletRequest
    ): String {

        var pagesize: Int = pageable.pageSize;
        val threads: Page<RecruitF> = friendRepository.selectNewThread(pageable)
        val threadDataList = mutableListOf<ThreadData>()
        val locale: Locale = RequestContextUtils.getLocale(request)

        threads.forEach { recruitF ->
            val inshiStringList = mutableListOf<String>()
            val inshiDescList = mutableListOf<String>()
            val inshiIdList = mutableListOf<Long>()
            val inshiLvList = mutableListOf<Int>()
            val inshiArray: List<String> = recruitF.getInshiArray()
            inshiArray.forEach { inshis ->

                val inshiList = inshiRepository.selectInshi(inshis.split(":")[0].toInt())
                if (inshiList.isNotEmpty()) {
                    val inshi = inshiList[0]
                    if (locale.language.equals("ko")) {
                        inshiStringList.add(inshi.kor_name)
                        inshiDescList.add(inshi.kor_desc)
                    } else {
                        inshiStringList.add(inshi.name)
                        inshiDescList.add(inshi.desc)
                    }
                    inshiIdList.add(inshi.id)
                    inshiLvList.add(inshis.split(":")[1].toInt())
                }
            }
            threadDataList.add(ThreadData(threads, recruitF, inshiStringList, inshiDescList, inshiIdList, inshiLvList))
        }

        model.addAttribute("threadDataList", threadDataList)
        model.addAttribute("pageSize", pagesize)
        return "board/friend/friendlist.html"
    }

    @GetMapping("/friend/post")
    fun postFriend(
        model: Model,
        @ModelAttribute recruitF: RecruitF,
        @SessionAttribute(name = "loginId", required = false) loginId: String?,
        @SessionAttribute(name = "nickname", required = false) nickname: String?
    ): String {
        if (loginId != null) {
            var ikuseilist = ikuseiRepository.selectIkusei()
            var supportlist = supportRepository.showSupportCard()
            var inshilist = inshiRepository.selectInshiAll()
            model.addAttribute("ikuseiList", ikuseilist)
            model.addAttribute("supportList", supportlist)
            model.addAttribute("inshiList", inshilist)
            return "board/friend/friendpost.html"
        }
            return "redirect:/login"
    }

    @PostMapping("/friend/submit")
    fun createThread(@Validated @ModelAttribute recruitF: RecruitF): String {
        // Save the new thread data using friendRepository
        friendRepository.save(recruitF)

        println("id" + recruitF.id)
        println("loginid" + recruitF.loginid)
        println("nickname" + recruitF.nickname)
        println("uma" + recruitF.represup)
        println("sup" + recruitF.repreuma)
        println("title" + recruitF.title)
        println("trainerid" + recruitF.trainerid)
        println("supportdeko" + recruitF.supportdeko)
        println("inshi" + recruitF.inshi)

        return "redirect:/friend"  // Redirect to the friend list page after posting
    }


}


data class ThreadData(
    val threads: Page<RecruitF>,
    val thread: RecruitF,
    val inshiString: List<String>,
    val inshiDesc: List<String>,
    val inshiId: List<Long>,
    val inshiLv: List<Int>
)
