package com.example.explorethedungeon.items

interface Item {
    val name: String

    val description: String
}

interface Weapon: Item {
    val damageMin: Int
    val damageRate: Int

    fun getDamage(dmgModifier: Float): Int {
        return (damageMin * dmgModifier + (0..(damageRate * dmgModifier).toInt()).random()).toInt()
    }

    //val damageType: Int
}

interface Armor: Item {
    val physDef: Float
    val magDef: Float

    fun reducePhysDmg(dmg: Int): Int {
        return( (dmg * (1 - physDef)).toInt() )
    }

    fun reduceMagDmg(dmg: Int): Int {
        return( (dmg * (1 - magDef)).toInt() )
    }
}