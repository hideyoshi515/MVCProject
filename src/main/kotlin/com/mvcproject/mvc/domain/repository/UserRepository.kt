package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * アカウントエンティティに対するリポジトリインターフェース。
 */
interface UserRepository : JpaRepository<Account, Long> {

    /**
     * 指定されたログインIDが存在するかどうかを確認します。
     *
     * @param loginId 確認するログインID。
     * @return ログインIDが存在する場合はtrue、それ以外の場合はfalse。
     */
    fun existsByLoginId(loginId: String): Boolean

    /**
     * 指定されたニックネームが存在するかどうかを確認します。
     *
     * @param nickname 確認するニックネーム。
     * @return ニックネームが存在する場合はtrue、それ以外の場合はfalse。
     */
    fun existsByNickname(nickname: String): Boolean

    /**
     * 指定されたログインIDに対応するアカウントを取得します。
     *
     * @param loginId 取得するアカウントのログインID。
     * @return ログインIDに対応するアカウントのOptional。存在しない場合は空のOptional。
     */
    fun findByLoginId(loginId: String): Optional<Account>
}
