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
class SupportCard {

    lateinit var chara:String
    lateinit var title:String
    lateinit var chara_id:String
    lateinit var rarity:String
    lateinit var exchange_item_id: String
    lateinit var effect_table_id:String
    lateinit var unique_effect_id:String
    lateinit var command_type:String
    lateinit var command_id:String
    lateinit var support_card_type:String
    lateinit var skill_set_id:String
    lateinit var detail_pos_x:String
    lateinit var detail_pos_y:String
    lateinit var detail_scale:String
    lateinit var detail_rot_z:String
    lateinit var start_date:String
    lateinit var outing_max:String
    lateinit var effect_id:String
    lateinit var jpn_name:String
    lateinit var kor_name:String
    lateinit var eng_name:String


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}