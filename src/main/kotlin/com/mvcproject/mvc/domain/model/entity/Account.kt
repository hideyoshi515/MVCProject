package com.mvcproject.mvc.domain.model.entity

import com.mvcproject.mvc.domain.model.UserRole
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Account {
    @Column(length = 45)
    lateinit var loginId:String

    @Column(length = 45)
    lateinit var password:String

    @Column(length = 45)
    lateinit var nickname:String

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    var role:UserRole = UserRole.USER

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}