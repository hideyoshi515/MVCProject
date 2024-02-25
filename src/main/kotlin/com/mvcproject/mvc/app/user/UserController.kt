package com.mvcproject.mvc.app.user

import com.mvcproject.mvc.domain.model.dto.LoginRequest
import com.mvcproject.mvc.domain.service.UserService
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.SessionAttribute

/**
 * ユーザーコントローラーはユーザー関連のリクエストを処理します。
 *
 * @param userService ユーザーサービスはユーザーに関する操作を提供します。
 */
@Controller
@RequiredArgsConstructor
class UserController(private val userService: UserService) {

    /**
     * "/mypage" エンドポイントへの GET リクエストを処理します。
     *
     * @param model Spring MVC モデル。
     * @param loginId セッションからの loginId 属性（存在する場合）。
     * @param nickname セッションからの nickname 属性（存在する場合）。
     * @param referer リクエストヘッダからの REFERER 情報。
     * @param request HTTP サーブレットリクエスト。
     * @return レンダリングするビューの名前。"/user/mypage.html" が返されます。
     */
    @GetMapping("/mypage")
    fun getToMypage(
        model: Model,
        @SessionAttribute(name = "loginId", required = false) loginId: String?,
        @SessionAttribute(name = "nickname", required = false) nickname: String?,
        @RequestHeader(value = HttpHeaders.REFERER, defaultValue = "") referer: String,
        request: HttpServletRequest
    ): String {
        // ログインしていない場合、ログインページにリダイレクトします。
        if (loginId.isNullOrBlank()) return "redirect:/login"

        // リファラ情報をセッションに保存します。
        val session = request.session
        session.setAttribute("originalUrl", referer)

        // モデルにユーザー情報を追加します。
        model.addAttribute("nickname", nickname)
        model.addAttribute("loginId", loginId)

        // マイページのビュー名を返します。
        return "/user/mypage.html"
    }
}
