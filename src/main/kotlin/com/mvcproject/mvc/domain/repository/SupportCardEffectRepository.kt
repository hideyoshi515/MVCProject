package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.SupportCardEffect
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SupportCardEffectRepository : JpaRepository<SupportCardEffect, Long> {
    @Cacheable("supportcardeffectall")
    @Query("Select u From SupportCardEffect u")
    fun showCardEffect(): List<SupportCardEffect>
    @Cacheable("supportcardeffect")
    @Query("Select u From SupportCardEffect u where u.id = :id")
    fun showSupportCardEffectByCardId(id:String): List<SupportCardEffect>

    @CacheEvict(
        value = ["supportcardeffectall","supportcardeffect"], allEntries = true
    )
    fun save(supportcardeffect: SupportCardEffect): SupportCardEffect
}