package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.Ikusei
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IkuseiRepository:JpaRepository<Ikusei, Long>{

    @Cacheable("selectIkuseiall")
    @Query("Select u From Ikusei u")
    fun selectIkusei(): List<Ikusei>

    @Cacheable("selectIkusei")
    @Query("Select u From Ikusei u Where u.uma_name = :uma")
    fun selectIkusei(uma:String): List<Ikusei>


    @CacheEvict(
        value = ["selectIkusei", "selectIkuseiall"],
        allEntries = true
    )
    fun save(ikusei: Ikusei): Ikusei

}