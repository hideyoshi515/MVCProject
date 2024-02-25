package com.mvcproject.mvc.domain.model.dto

import com.mvcproject.mvc.domain.model.UserRole

/**
 * ログインリクエストを表すデータクラス。
 */
class LoginRequest {
    var id: Long? = null
    var loginId: String = ""
    var password: String? = null
    var nickname: String? = null
    var role: UserRole? = null
}
