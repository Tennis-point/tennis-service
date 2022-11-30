package com.tei.tenis.point.tenis.infrastracture.db

import javax.persistence.*


@Table(name = "TENNIS_GEM")
@Entity
class GemEntity(
    @Id val gemId: String = "",
    val p1Points: String = "0",
    val p2Points: String = "0",
    val p1Tie: String = "0",
    val p2Tie: String = "0",
    val winner: String? = null
) {

}