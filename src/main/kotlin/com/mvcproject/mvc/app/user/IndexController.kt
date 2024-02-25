package com.mvcproject.mvc.app.user

import com.mvcproject.mvc.domain.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttribute
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.util.Locale

/**
 * アプリケーションインデックスに関連するリクエストを処理するコントローラー。
 *
 * @param userService ユーザー関連の操作を担当するサービス。
 */
@Controller
class IndexController(private val userService: UserService) {

    /**
     * ルート ("/") または "/index.html" エンドポイントへの GET リクエストを処理します。
     *
     * @param lang オプションの言語パラメータ。
     * @param locale 現在のロケール。
     * @param request HTTP サーブレットリクエスト。
     * @param model Spring MVC モデル。
     * @param redirectAttributes リダイレクト属性。
     * @param loginId セッションからの loginId 属性（存在する場合）。
     * @param nickname セッションからの nickname 属性（存在する場合）。
     * @return レンダリングするビューの名前。この場合、"/index.html"。
     */
    @GetMapping(value = ["", "/"])
    fun getToIndex(
        @RequestParam(required = false) lang: String?,
        locale: Locale,
        request: HttpServletRequest,
        model: Model,
        redirectAttributes: RedirectAttributes,
        @SessionAttribute(name = "loginId", required = false) loginId: String?,
        @SessionAttribute(name = "nickname", required = false) nickname: String?
    ): String {
        // "lang" パラメータが提供された場合、ロケールを設定します。
        if (!lang.isNullOrBlank()) {
            LocaleContextHolder.setLocale(Locale(lang))
        }

        // ユーザーがログインしている場合、モデルにその情報を追加します。
        if (loginId != null) {
            model.addAttribute("loginId", loginId)
            model.addAttribute("nickname", nickname)
        }

        // モデルにタイトル属性を設定します。
        model["title"] = "テスト" // 適切なタイトルに置き換えてください。

        // レンダリングするビューの名前を返します。
        return "/index.html"
    }
}