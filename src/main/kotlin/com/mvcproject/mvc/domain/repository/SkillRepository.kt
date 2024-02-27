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
    @Query("Select u From Skill u")
    fun showSkill(): List<Skill>

    @Cacheable("skillsTC")
    @Query("Select u From Skill u Order By u.type Asc, u.color ASC, u.skillcode ASC")
    fun showSkillByTypeCode(): List<Skill>
    
    @Cacheable("skillsC")
    @Query("Select u From Skill u Order By u.skillcode ASC")
    fun showSkillByCode(): List<Skill>

    @CacheEvict(
        value = ["skills","skillsTC","skillsC"],
        allEntries = true
    )
    fun save(skill: Skill): Skill
}