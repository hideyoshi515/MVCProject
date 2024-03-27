package com.mvcproject.mvc.app.board

import com.mvcproject.mvc.domain.model.entity.RecruitF
import com.mvcproject.mvc.domain.repository.IkuseiRepository
import com.mvcproject.mvc.domain.repository.InshiRepository
import com.mvcproject.mvc.domain.repository.RecruitFRepository
import com.mvcproject.mvc.domain.repository.SupportCardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class FriendController(private val friendRepository: RecruitFRepository, private val ikuseiRepository:IkuseiRepository, private val supportRepository:SupportCardRepository, private val inshiRepository: InshiRepository) {

    @GetMapping("/friend")
    fun showBoard(model: Model, @PageableDefault(size = 7) pageable: Pageable): String {

        var pagesize: Int = pageable.pageSize;
        val threads: Page<RecruitF> = friendRepository.selectThread(pageable)
        model.addAttribute("threads", threads)
        model.addAttribute("pageSize", pagesize)
        return "board/friend/friendlist.html"
    }

    @GetMapping("/friend/post")
    fun postFriend(model: Model, @RequestBody post: RecruitF): String {
        var ikuseilist = ikuseiRepository.selectIkusei()
        var supportlist = supportRepository.showSupportCard()
        var inshilist = inshiRepository.selectInshiAll()
        model.addAttribute("ikuseiList", ikuseilist)
        model.addAttribute("supportList", supportlist)
        model.addAttribute("inshiList", inshilist)
        return "board/friend/friendpost.html"
    }


}
