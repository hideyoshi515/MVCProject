package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.Ikusei
import com.mvcproject.mvc.domain.model.entity.Inshi
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InshiRepository:JpaRepository<Inshi, Long>{

    @Cacheable("selectInshi")
    @Query("Select u From Inshi u")
    fun selectInshiAll(): List<Inshi>

    @Query("Select u From Inshi u where u.id = :id")
    fun selectInshi(id:Int): List<Inshi>

    @CacheEvict(
        value = ["selectInshi"],
        allEntries = true
    )
    fun save(inshi: Inshi): Inshi

}
