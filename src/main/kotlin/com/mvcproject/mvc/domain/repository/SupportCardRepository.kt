package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.SupportCard
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SupportCardRepository : JpaRepository<SupportCard, Long> {

    @Cacheable("supportcard")
    @Query("Select u From SupportCard u")
    fun showSupportCard(): List<SupportCard>
    @Cacheable("supportcardChar")
    @Query("Select u From SupportCard u where u.chara = :uma")
    fun showSupportCardByChar(uma:String): List<SupportCard>
    @Cacheable("supportcardId")
    @Query("Select u From SupportCard u where u.id = :id")
    fun showSupportCardById(id:String): List<SupportCard>

    @CacheEvict(
        value = ["supportcard","supportcardChar","supportcardId"],
        allEntries = true
    )
    fun save(supportcard: SupportCard): SupportCard
}