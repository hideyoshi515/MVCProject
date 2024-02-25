package com.mvcproject.mvc.app.login

import com.mvcproject.mvc.domain.model.dto.LoginRequest
import com.mvcproject.mvc.domain.model.entity.Account
import com.mvcproject.mvc.domain.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.WebUtils
import java.util.*
import kotlin.collections.ArrayList

@Controller
@RequiredArgsConstructor
class LoginController(private val userService: UserService) {

    /**
     * ログインページに遷移するためのGETリクエストを処理します。
     *
     * @param model Spring MVC モデル。
     * @param loginId セッションからの loginId 属性（存在する場合）。
     * @param nickname セッションからの nickname 属性（存在する場合）。
     * @param lastLoginId クッキーからの最後のログインID。
     * @param referer リクエストヘッダからの REFERER。
     * @param request HTTPリクエスト。
     * @return レンダリングするビューの名前。ログインユーザーが存在する場合はマイページにリダイレクトされます。
     */
    @GetMapping("/login")
    fun getToLogin(
        model: Model,
        @SessionAttribute(name = "loginId", required = false) loginId: String?,
        @SessionAttribute(name = "nickname", required = false) nickname: String?,
        @CookieValue(value = "lastLoginId", defaultValue = "") lastLoginId: String,
        @RequestHeader(value = HttpHeaders.REFERER, defaultValue = "") referer: String,
        request: HttpServletRequest
    ): String {
        val loginUser = userService.getLoginUserByLoginId(loginId)

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.nickname)
            model.addAttribute("loginId", loginUser.loginId)
            return "redirect:/mypage"
        }
        val session = request.session
        session.setAttribute("originalUrl", referer)

        model.addAttribute("loginRequest", LoginRequest())
        return "/login/login.html"
    }

    /**
     * ログインを試みるためのPOSTリクエストを処理します。
     *
     * @param loginRequest フォームデータを受け取るオブジェクト。
     * @param bindingResult バリデーションエラーを保持するオブジェクト。
     * @param httpServletRequest HTTPリクエスト。
     * @return レンダリングするビューの名前。エラーがあればエラー画面にリダイレクトされます。
     */
    @PostMapping("/login")
    fun login(
        @ModelAttribute loginRequest: LoginRequest,
        bindingResult: BindingResult,
        httpServletRequest: HttpServletRequest
    ): String {
        val user: Account? = userService.login(loginRequest)

        // ログインアイディまたはパスワードが間違った場合、グローバルエラーを返す
        if (user == null) {
            bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.")
        }
        if (bindingResult.hasErrors()) {
            return "/login/loginerror"
        }

        // ログイン成功 => セッションを作成
        // セッションを作成する前に既存のセッションを破棄
        //httpServletRequest.session.invalidate()
        val session = httpServletRequest.getSession(true) // セッションが存在しない場合は作成
        if (sessionList.containsValue(session)) {
            sessionList.remove(session.id, session)
            session.removeAttribute("loginId")
            session.removeAttribute("nickname")
        }

        val originalUrl = session.getAttribute("originalUrl") as? String
        session.removeAttribute("originalUrl")
        // セッションにuserIdをセット
        session.setAttribute("loginId", user?.loginId)
        session.setAttribute("nickname", user?.nickname)
        session.maxInactiveInterval = 1800 // セッションが30分間維持
        sessionList.put(session.id, session)
        return if (!originalUrl.isNullOrBlank()) {
            "redirect:$originalUrl"
        }  else {
            return "redirect:/"
        }
    }

    /**
     * ログアウトを処理するためのGETリクエストを処理します。
     *
     * @param request HTTPリクエスト。
     * @return レンダリングするビューの名前。ログアウト後に元のURLがある場合はそのURLにリダイレクトされます。
     */
    @GetMapping("/logout")
    fun logout(
        request: HttpServletRequest
    ): String {
        val session = request.getSession(false) // セッションが存在しない場合はnullを返す
        val originalUrl = session.getAttribute("originalUrl") as? String
        session.removeAttribute("originalUrl")
        if (session != null) {
            sessionList.remove(session.id)
            session.invalidate()
        }
        return if (!originalUrl.isNullOrBlank()) {
            "redirect:$originalUrl"
        }  else {
            return "redirect:/"
        }
    }

    // セッションリスト
    var sessionList: Hashtable<String, Any> = Hashtable()

    /**
     * セッションリストを返すためのGETリクエストを処理します。
     *
     * @return セッションIDとそれに関連する情報を含むマップ。
     */
    @GetMapping("/session-list")
    @ResponseBody
    fun sessionList(): MutableMap<String, ArrayList<String>> {
        val elements: Enumeration<*> = sessionList.elements()
        val lists: MutableMap<String, ArrayList<String>> = HashMap()
        while (elements.hasMoreElements()) {
            var sessiondata: ArrayList<String> = ArrayList<String>()
            val session = elements.nextElement() as HttpSession
            sessiondata.add(0, "loginId:" + session.getAttribute("loginId").toString())
            sessiondata.add(1, "nickname:" + session.getAttribute("nickname").toString())
            lists[session.id] = sessiondata//session.getAttribute("loginId").toString()
        }
        return lists
    }
}
