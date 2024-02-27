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
class Skill {

    lateinit var jpn_name:String
    lateinit var kor_name: String
    lateinit var targetids:String
    lateinit var jpn_effect:String
    lateinit var kor_effect:String
    lateinit var tags:String
    lateinit var jpn_evol:String
    lateinit var kor_evol:String
    lateinit var img:String
    lateinit var color:String
    lateinit var type:Integer
    lateinit var skillcode:Integer
    lateinit var precondition1:String
    lateinit var condition1:String
    lateinit var precondition2:String
    lateinit var condition2:String

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}