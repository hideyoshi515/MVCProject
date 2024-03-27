package com.mvcproject.mvc.domain.model.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
class RecruitF {
    lateinit var title: String

    @Column(name = "nickname")
    lateinit var nickname: String

    @Column(name = "loginid")
    lateinit var loginid: String

    @Column(name = "repreuma")
    var repreuma: Int = 0

    @Column(name = "represup")
    var represup: Int = 0

    @Column(name = "trainerid")
    var trainerid: Int = 0

    @Column(name = "supportdeko")
    var supportdeko: Int = 0

    @Column(name = "inshi")
    lateinit var inshi: String

    fun getInshiArray(): List<String> {
        if (!::inshi.isInitialized || inshi.isNullOrEmpty()) {
            return emptyList()
        }
        return inshi.split(",").map { it.trim() }
    }

    fun setInshiArray(array: List<String>) {
        inshi = array.joinToString(",")
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}