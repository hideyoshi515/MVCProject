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
class SupportCardEffect {

    lateinit var id:String
    lateinit var type:String
    lateinit var init:String
    lateinit var limit_lv5:String
    lateinit var limit_lv10:String
    lateinit var limit_lv15: String
    lateinit var limit_lv20:String
    lateinit var limit_lv25:String
    lateinit var limit_lv30:String
    lateinit var limit_lv35:String
    lateinit var limit_lv40:String
    lateinit var limit_lv45:String
    lateinit var limit_lv50:String


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var pk: Long = 0L
}