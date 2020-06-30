package com.example.explorethedungeon.character

val raceList = arrayOf(
    "Human",
    "Goblin",
    "Elf",
    "Ork"
)

interface CharRace {
    val name: String
    val stats: RawStats
}

fun getCharRace(rc: String?): CharRace {
    return when(rc){
        "Human" -> Human()
        "Goblin" -> Goblin()
        "Elf" -> Elf()
        "Ork" -> Ork()
        else -> NullRace()
    }
}

class NullRace: CharRace {
    override val name = "NullRace"
    override val stats = RawStats(0,0,0,0,0)
}

class Goblin: CharRace {
    override val name = "Goblin"
    override val stats = RawStats(3,6,9,10,4)
}

class Human: CharRace {
    override val name = "Human"
    override val stats = RawStats(7,8,6,6,6)
}

class Elf: CharRace {
    override val name = "Elf"
    override val stats = RawStats(5,6,7,9,9)
}

class Ork: CharRace {
    override val name = "Ork"
    override val stats = RawStats(12,12,2,3,3)
}