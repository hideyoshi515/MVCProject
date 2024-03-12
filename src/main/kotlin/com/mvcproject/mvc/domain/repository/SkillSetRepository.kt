package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.Skill
import com.mvcproject.mvc.domain.model.entity.SkillSet
import com.mvcproject.mvc.domain.model.entity.SupportCard
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

@EnableCaching
interface SkillSetRepository : JpaRepository<SkillSetRepository, Long> {
    @Cacheable("skillset")
    @Query("Select u From SkillSet u")
    fun showSkillSet(): List<SkillSet>


    @Cacheable("skillssettarget")
    @Query("Select u From SkillSet u where u.id = :id")
    fun showSkillSetById(id:String): List<SkillSet>

    @CacheEvict(
        value = ["skillset","skillssetTarget"],
        allEntries = true
    )
    fun save(skillset: SkillSet): SkillSet
}