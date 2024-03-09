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
class Ikusei {

    lateinit var dressid:String
    lateinit var umamusumeid:String
    lateinit var uma_name:String
    lateinit var jpn_name:String
    lateinit var kor_name: String
    lateinit var speed: String
    lateinit var stamina: String
    lateinit var power: String
    lateinit var guts: String
    lateinit var wisdom: String

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}