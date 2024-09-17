package com.eduardodiniz.mancala.domain.domain

class Pit(
    val index: Int,
    var stones: Int
) {

    fun addStone() {
        this.stones++
    }

    fun removeAllSeeds() {
        this.stones = 0
    }

    fun addStone(stones: Int) {
        this.stones += stones
    }

}