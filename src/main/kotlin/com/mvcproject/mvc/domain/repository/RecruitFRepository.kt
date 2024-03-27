package com.mvcproject.mvc.domain.repository

import com.mvcproject.mvc.domain.model.entity.RecruitF
import com.mvcproject.mvc.domain.model.entity.Umamusume
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RecruitFRepository : JpaRepository<RecruitF, Long> {

    @Query("SELECT u FROM RecruitF u")
    fun selectThread(pageable: Pageable): Page<RecruitF>
    @Query("SELECT u FROM RecruitF u order by u.id desc")
    fun selectNewThread(pageable: Pageable): Page<RecruitF>



}