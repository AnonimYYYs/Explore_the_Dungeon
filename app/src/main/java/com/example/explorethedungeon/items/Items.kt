package com.example.explorethedungeon.items

interface Item {
    val name: String

    val description: String
}

interface Weapon: Item {
    val damageMin: Int
    val damageRate: Int

    val damageType: Int
    /* 0 - phys, 1 - magic*/

    fun getDamage(dmgModifier: Float): Int {
        return (damageMin * dmgModifier + (0..( (damageRate * dmgModifier).toInt() )).random() ).toInt()
    }

}

const val minimumDamage = 0.3f

interface Armor: Item {
    val physDef: Float
    val magDef: Float

    fun reducedDmg(dmg: Int, defenceModifier: Float, type: Int): Int {
        var dmgPercent = 1.0f
        when(type){
            0 -> dmgPercent -= physDef * defenceModifier
            1 -> dmgPercent -= magDef * defenceModifier
        }
        if(dmgPercent < minimumDamage){dmgPercent = minimumDamage}
        return(dmg * dmgPercent).toInt()
    }

}