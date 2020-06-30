package com.example.explorethedungeon.items

class BasicArmor : Armor {
    override val name = "Basic Armor"
    override val description = "basic equipment of warrior classes"
    override val physDef = 0.1f
    override val magDef = 0.04f
}

class BasicRobe : Armor {
    override val name = "Basic Robe"
    override val description = "basic equipment of mage classes"
    override val physDef = 0.04f
    override val magDef = 0.1f
}

class BasicShirt : Armor {
    override val name = "Basic Armor"
    override val description = "basic equipment of rogue classes"
    override val physDef = 0.07f
    override val magDef = 0.07f
}