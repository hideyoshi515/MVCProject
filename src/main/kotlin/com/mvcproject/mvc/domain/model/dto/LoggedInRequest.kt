package com.mvcproject.mvc.domain.model.dto

import com.mvcproject.mvc.domain.model.UserRole
import com.mvcproject.mvc.domain.model.entity.Account
import jakarta.validation.constraints.NotBlank

/**
 * ログイン情報のリクエストを表すデータクラス。
 *
 * @param loginId ログインID。
 * @param password パスワード。
 * @param passwordCheck パスワード確認用。
 * @param nickname ニックネーム。
 */
class LoggedInRequest(
    @NotBlank(message = "ログインアカウントが空欄です。")
    var loginId: String = "",

    @NotBlank(message = "パスワードが空欄です。")
    var password: String = "",

    var passwordCheck: String = "",

    @NotBlank(message = "ニックネームが空欄です。")
    var nickname: String = ""
) {
    /**
     * リクエストデータをエンティティに変換します。
     *
     * @return 変換されたアカウントエンティティ。
     */
    fun toEntity(): Account = Account().also { user ->
        user.loginId = this.loginId
        user.password = this.password
        user.nickname = this.nickname
        user.role = UserRole.USER
    }

    /**
     * エンコード済みのパスワードを使用して、リクエストデータをエンティティに変換します。
     *
     * @param encodedPassword エンコードされたパスワード。
     * @return 変換されたアカウントエンティティ。
     */
    fun toEntity(encodedPassword: String): Account = Account().also { user ->
        user.loginId = this.loginId
        user.password = encodedPassword
        user.nickname = this.nickname
        user.role = UserRole.USER
    }
}
