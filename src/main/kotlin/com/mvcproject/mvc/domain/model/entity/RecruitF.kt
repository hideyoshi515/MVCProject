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
    var uma: Int = 0

    @Column(name = "represup")
    var support: Int = 0

    @Column(name = "trainerid")
    var trainderid: Int = 0

    @Column(name = "supportdeko")
    var supportdeko: Int = 0

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}