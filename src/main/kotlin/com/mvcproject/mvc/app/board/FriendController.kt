package com.mvcproject.mvc.app.board

import com.mvcproject.mvc.domain.model.entity.RecruitF
import com.mvcproject.mvc.domain.repository.RecruitFRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
class FriendController(private val friendRepository: RecruitFRepository) {


    @GetMapping("/friend")
    fun showBoard(model: Model, @PageableDefault(size = 10) pageable: Pageable): String {
        val threads: Page<RecruitF> = friendRepository.selectThread(pageable)
        model.addAttribute("threads", threads)
        return "board/friend/index.html"
    }


}
