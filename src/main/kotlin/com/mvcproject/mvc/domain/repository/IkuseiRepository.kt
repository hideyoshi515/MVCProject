package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.Ikusei
import com.mvcproject.mvc.domain.model.entity.SupportCard
import com.mvcproject.mvc.domain.model.entity.Umamusume
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IkuseiRepository:JpaRepository<Ikusei, Long>{

    @Cacheable("selectIkusei")
    @Query("Select u From Ikusei u Where u.uma_name = :uma")
    fun selectIkusei(uma:String): List<Ikusei>


    @CacheEvict(
        value = ["selectIkusei"],
        allEntries = true
    )
    fun save(ikusei: Ikusei): Ikusei

}