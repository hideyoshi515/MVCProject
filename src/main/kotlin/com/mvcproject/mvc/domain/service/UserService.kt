package com.mvcproject.mvc.domain.service

import com.mvcproject.mvc.domain.model.dto.LoggedInRequest
import com.mvcproject.mvc.domain.model.dto.LoginRequest
import com.mvcproject.mvc.domain.model.entity.Account
import com.mvcproject.mvc.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * ユーザーサービスクラス。
 *
 * @property userRepository ユーザーアカウントに関するデータアクセスを提供するリポジトリ。
 */
@Service
class UserService(private val userRepository: UserRepository) {

    /**
     * 指定されたログインIDがすでに存在するかどうかを確認します。
     *
     * @param loginId 確認するログインID。
     * @return ログインIDが存在する場合はtrue、それ以外の場合はfalse。
     */
    fun checkLoginIdDuplicate(loginId: String?): Boolean {
        return loginId?.let { userRepository.existsByLoginId(it) } ?: false
    }

    /**
     * 指定されたニックネームがすでに存在するかどうかを確認します。
     *
     * @param nickname 確認するニックネーム。
     * @return ニックネームが存在する場合はtrue、それ以外の場合はfalse。
     */
    fun checkNicknameDuplicate(nickname: String?): Boolean {
        return nickname?.let { userRepository.existsByNickname(it) } ?: false
    }

    /**
     * ユーザーを登録します。
     *
     * @param req 登録するユーザー情報。
     */
    @Transactional
    fun join(req: LoggedInRequest) {
        userRepository.save(req.toEntity())
    }

    /**
     * 指定されたログイン情報をもとにユーザーを検索してログインします。
     *
     * @param req ログイン情報。
     * @return ログインが成功した場合は対応するアカウント、それ以外の場合はnull。
     */
    @Transactional(readOnly = true)
    fun login(req: LoginRequest): Account? {
        return userRepository.findByLoginId(req.loginId)
            .filter { it.password == req.password }
            .orElse(null)
    }

    /**
     * 指定されたユーザーIDに対応するユーザーを取得します。
     *
     * @param userId 取得するユーザーのID。
     * @return ユーザーIDに対応するアカウント。存在しない場合はnull。
     */
    @Transactional(readOnly = true)
    fun getLoginUserById(userId: Long?): Account? {
        return userId?.let { userRepository.findById(it).orElse(null) }
    }

    /**
     * 指定されたログインIDに対応するユーザーを取得します。
     *
     * @param loginId 取得するユーザーのログインID。
     * @return ログインIDに対応するアカウント。存在しない場合はnull。
     */
    @Transactional(readOnly = true)
    fun getLoginUserByLoginId(loginId: String?): Account? {
        return loginId?.let { userRepository.findByLoginId(it).orElse(null) }
    }
}
