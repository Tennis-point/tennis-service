package com.tei.tenis.point.tenis.infrastracture.db

import javax.persistence.*;


@Table(name = "TENNIS_SET")
@Entity
class SetEntity(
    @Id val setId: String = "",
    val winner: String? = null,
    @OneToMany(orphanRemoval = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "setId")
    val gems: MutableList<GemEntity> = mutableListOf()
) {

}