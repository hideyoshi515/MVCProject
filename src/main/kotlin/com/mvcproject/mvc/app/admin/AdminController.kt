package com.mvcproject.mvc.app.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 管理者関連のリクエストを処理するコントローラー。
 */
@Controller
class AdminController {

    /**
     * "/admin" エンドポイントへの GET リクエストを処理します。
     *
     * @param model Spring MVC モデル。
     * @return レンダリングするビューの名前。"/admin/admin.html" が返されます。
     */
    @RequestMapping("/admin")
    fun getToAdmin(model: Model): String {
        return "/admin/admin.html"
    }
}
