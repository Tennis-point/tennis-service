package com.tei.tenis.point.domain.game

class Set(val gems: MutableList<Gem>) {
    var setId: String? = null
    var winner: Player? = null
    var done: Boolean = false;
}