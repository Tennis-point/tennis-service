package com.tei.tenis.point.tenis.domain.game

class Set(val gems: MutableList<Gem>) {

    var winner: Player? = null
    var done: Boolean = false;

}