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
class Umamusume {

    lateinit var name:String
    lateinit var jpn_name:String
    lateinit var kor_name: String
    lateinit var eng_name:String
    lateinit var img:String
    lateinit var main_color:String
    lateinit var sub_color:String
    lateinit var CV:String
    lateinit var Order:String
    lateinit var CharType:String

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}