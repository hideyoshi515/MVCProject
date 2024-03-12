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
class SkillSet {

    lateinit var skill_id1: String
    lateinit var skill_level1: String
    lateinit var skill_id2: String
    lateinit var skill_level2: String
    lateinit var skill_id3: String
    lateinit var skill_level3: String
    lateinit var skill_id4: String
    lateinit var skill_level4: String
    lateinit var skill_id5: String
    lateinit var skill_level5: String
    lateinit var skill_id6: String
    lateinit var skill_level6: String
    lateinit var skill_id7: String
    lateinit var skill_level7: String
    lateinit var skill_id8: String
    lateinit var skill_level8: String
    lateinit var skill_id9: String
    lateinit var skill_level9: String
    lateinit var skill_id10: String
    lateinit var skill_level10: String
    lateinit var skill_id11: String
    lateinit var skill_level11: String
    lateinit var skill_id12: String
    lateinit var skill_level12: String
    lateinit var skill_id13: String
    lateinit var skill_level13: String
    lateinit var skill_id14: String
    lateinit var skill_level14: String
    lateinit var skill_id15: String
    lateinit var skill_level15: String
    lateinit var skill_id16: String
    lateinit var skill_level16: String
    lateinit var skill_id17: String
    lateinit var skill_level17: String
    lateinit var skill_id18: String
    lateinit var skill_level18: String
    lateinit var skill_id19: String
    lateinit var skill_level19: String
    lateinit var skill_id20: String
    lateinit var skill_level20: String

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}
