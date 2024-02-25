package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.Skill
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

@EnableCaching
interface SkillRepository : JpaRepository<Skill, Long> {
    @Cacheable("skills")
    @Query("select u From Skill u")
    fun showSkill(): List<Skill>

    @CacheEvict(
        value = ["skills","dirt","sprint","mile","intermediate","long"],
        allEntries = true
    )
    fun save(skill: Skill): Skill
}