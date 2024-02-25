package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.Umamusume
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Umamusumeエンティティ用のリポジトリインターフェース。
 */
@EnableCaching
interface UmamusumeRepository : JpaRepository<Umamusume, Long> {

    /**
     * リリース日の昇順でソートされたUmamusumeエンティティのリストを取得します。
     * 結果はキー "releasedate" でキャッシュされます。
     */
    @Cacheable("releasedate")
    @Query("select u From Umamusume u")
    fun showUmamusume(): List<Umamusume>

    /**
     * リリース日の降順でソートされたUmamusumeエンティティのリストを取得します。
     * 結果はキー "releasedateReverse" でキャッシュされます。
     */
    @Cacheable("releasedateReverse")
    @Query("select u From Umamusume u order by u.id Desc")
    fun showUmamusumeReverse(): List<Umamusume>

    /**
     * 韓国名で昇順にソートされたUmamusumeエンティティのリストを取得します。
     * 結果はキー "asckor" でキャッシュされます。
     */
    @Cacheable("asckor")
    @Query("select u From Umamusume u order by u.kor_name ASC")
    fun showUmamusumeAscKor(): List<Umamusume>

    /**
     * 韓国名で降順にソートされたUmamusumeエンティティのリストを取得します。
     * 結果はキー "desckor" でキャッシュされます。
     */
    @Cacheable("desckor")
    @Query("select u From Umamusume u order by u.kor_name DESC")
    fun showUmamusumeDescKor(): List<Umamusume>

    /**
     * 日本名で昇順にソートされたUmamusumeエンティティのリストを取得します。
     * 結果はキー "ascjpn" でキャッシュされます。
     */
    @Cacheable("ascjpn")
    @Query("select u From Umamusume u order by u.jpn_name ASC")
    fun showUmamusumeAscJpn(): List<Umamusume>

    /**
     * 日本名で降順にソートされたUmamusumeエンティティのリストを取得します。
     * 結果はキー "descjpn" でキャッシュされます。
     */
    @Cacheable("descjpn")
    @Query("select u From Umamusume u order by u.jpn_name DESC")
    fun showUmamusumeDescJpn(): List<Umamusume>

    /**
     * 英語名で昇順にソートされたUmamusumeエンティティのリストを取得します。
     * 結果はキー "asceng" でキャッシュされます。
     */
    @Cacheable("asceng")
    @Query("select u From Umamusume u order by u.eng_name ASC")
    fun showUmamusumeAscEng(): List<Umamusume>

    /**
     * 英語名で降順にソートされたUmamusumeエンティティのリストを取得します。
     * 結果はキー "desceng" でキャッシュされます。
     */
    @Cacheable("desceng")
    @Query("select u From Umamusume u order by u.eng_name DESC")
    fun showUmamusumeDescEng(): List<Umamusume>

    /**
     * Umamusumeエンティティのキャッシュエントリを無効化します。
     * キャッシュのエントリを無効化するためにsaveメソッドをオーバーライドします。
     */
    @CacheEvict(
        value = ["releasedate", "asckor", "desckor", "ascjpn", "descjpn", "asceng", "desceng","releasedateReverse"],
        allEntries = true
    )
    fun save(umamusume: Umamusume): Umamusume
}
