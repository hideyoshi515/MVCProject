package com.mvcproject.mvc.app.register

import com.mvcproject.mvc.domain.model.dto.LoggedInRequest
import com.mvcproject.mvc.domain.service.UserService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.SessionAttribute
import java.util.regex.Pattern

/**
 * ユーザー登録に関連するリクエストを処理するコントローラー。
 *
 * @param userService ユーザーサービスはユーザーに関する操作を提供します。
 */
@Controller
@RequiredArgsConstructor
class RegisterController(private val userService: UserService) {

    /**
     * "/register" エンドポイントへの GET リクエストを処理します。
     *
     * @param model Spring MVC モデル。
     * @param loginId セッションからの loginId 属性（存在する場合）。
     * @param nickname セッションからの nickname 属性（存在する場合）。
     * @return レンダリングするビューの名前。"/register/register.html" が返されます。
     */
    @GetMapping("/register")
    fun getToRegister(
        model: Model,
        @SessionAttribute(name = "loginId", required = false) loginId: String?,
        @SessionAttribute(name = "nickname", required = false) nickname: String?
    ): String {
        if (loginId != null) {
            model.addAttribute("nickname", nickname)
            model.addAttribute("loginId", loginId)
            return "redirect:/"
        }
        model.addAttribute("registerRequest", LoggedInRequest())
        return "/register/register.html"
    }

    /**
     * "/register" エンドポイントへの POST リクエストを処理します。
     *
     * @param registerRequest フォームデータを受け取るオブジェクト。
     * @param bindingResult バリデーションエラーを保持するオブジェクト。
     * @return レンダリングするビューの名前。エラーがあればエラー画面にリダイレクトされます。
     */
    @PostMapping("/register")
    fun join(@ModelAttribute registerRequest: @Valid LoggedInRequest, bindingResult: BindingResult): String {
        // loginId 重複チェック
        var banned_nick =
            arrayOf("관리자", "운영자", "매니저", "admin", "Admin", "Administrator", "Manager", "Manager", "管理者", "運営者")
        if (userService.checkLoginIdDuplicate(registerRequest.loginId)) {
            bindingResult.addError(FieldError("registerRequest", "loginId", "이미 존재하는 아이디입니다."))
        }
        // ニックネーム重複チェックおよび禁止ニックネーム・正規表現チェック
        if (userService.checkNicknameDuplicate(registerRequest.nickname) || banned_nick.indexOf(registerRequest.nickname) > -1 || !Pattern.matches(
                "^[a-zA-Z0-9]*$",
                registerRequest.nickname
            )
        ) {
            bindingResult.addError(FieldError("registerRequest", "nickname", "이미 존재하는 닉네임입니다."))
        }
        // パスワード長および特殊文字チェック
        if (!Pattern.matches("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", registerRequest.password)) {
            bindingResult.addError(FieldError("registerRequest", "password", "비밀번호는 영문+숫자+특수문자 조합하여 8~16자리를 사용해야 합니다."))
        }
        // パスワードとパスワード確認が一致しているかチェック
        if (!registerRequest.password.equals(registerRequest.passwordCheck)) {
            bindingResult.addError(FieldError("registerRequest", "passwordCheck", "바밀번호가 일치하지 않습니다."))
        }
        // エラーがある場合、該当するエラー画面にリダイレクト
        if (bindingResult.hasErrors()) {
            when {
                bindingResult.getFieldError("loginId") != null -> return "register/loginIderror"
                bindingResult.getFieldError("nickname") != null -> return "register/nicknameerror"
                bindingResult.getFieldError("password") != null -> return "register/passworderror"
                bindingResult.getFieldError("passwordCheck") != null -> return "register/passcheckerror"
            }
        }
        // ユーザー登録処理
        userService.join(registerRequest)
        return "register/registersuccess"
    }
}
