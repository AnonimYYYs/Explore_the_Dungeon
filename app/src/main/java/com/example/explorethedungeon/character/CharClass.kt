package com.example.explorethedungeon.character

val classList = arrayOf(
    "Warrior",
    "Mage",
    "Rogue"
)

interface CharClass {
    val name: String
    val stats: RawStats
}

fun getCharClass(cls: String?): CharClass {
    return when(cls){
        "Warrior"-> Warrior()
        "Mage" -> Mage()
        "Rogue" -> Rogue()
        else -> NullClass()
    }
}

class NullClass: CharClass {
    override val name = "NullClass"
    override val stats = RawStats(0,0,0,0,0)
}

class Warrior: CharClass {
    override val name = "Warrior"
    override val stats = RawStats(10,10,6,3,3)
}

class Mage: CharClass {
    override val name = "Mage"
    override val stats = RawStats(4,6,5,10,10)
}

class Rogue: CharClass {
    override val name = "Rogue"
    override val stats = RawStats(7,7,10,5,5)
}

