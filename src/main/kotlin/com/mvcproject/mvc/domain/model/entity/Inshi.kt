package com.mvcproject.mvc.domain.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Inshi {

    lateinit var kor_name:String
    lateinit var kor_desc:String
    lateinit var name:String
    lateinit var desc:String

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}