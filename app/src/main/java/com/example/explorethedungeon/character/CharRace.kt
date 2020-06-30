package com.example.explorethedungeon.character

val raceList = arrayOf(
    "Human",
    "Goblin",
    "Elf"
)

interface CharRace {
    val name: String
    val maxHp: Int
    val physicDamageModifier: Float
}

fun getCharRace(rc: String?): CharRace {
    return when(rc){
        "Human" -> Human()
        "Goblin" -> Goblin()
        "Elf" -> Elf()
        else -> NullRace()
    }
}

class NullRace: CharRace {
    override val name = "NullRace"
    override val maxHp = 0
    override val physicDamageModifier = 0.0f
}

class Goblin: CharRace {
    override val name = "Goblin"
    override val maxHp = 100
    override val physicDamageModifier = 0.5f
}

class Human: CharRace {
    override val name = "Human"
    override val maxHp = 250
    override val physicDamageModifier = 1.0f
}

class Elf: CharRace {
    override val name = "Elf"
    override val maxHp = 200
    override val physicDamageModifier = 0.9f
}