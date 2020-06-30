package com.example.explorethedungeon.character

val classList = arrayOf(
    "Warrior",
    "Mage",
    "Rogue"
)

interface CharClass {
    val name: String
    val physicDamageModifier: Float
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
    override val physicDamageModifier = 0.0f
}

class Warrior: CharClass {
    override val name = "Warrior"
    override val physicDamageModifier = 10.0f
}

class Mage: CharClass {
    override val name = "Mage"
    override val physicDamageModifier = 5.0f
}

class Rogue: CharClass {
    override val name = "Rogue"
    override val physicDamageModifier = 13.0f
}

