package com.example.explorethedungeon.character

import com.example.explorethedungeon.items.*

fun newRandomMob(): Character<CharRace, CharClass> {
    val mobRace = getCharRace((raceList).random())
    val mobClass = getCharClass((classList).random())
    return Character(mobRace,mobClass)
}

class Character<R, C>(val Race: R, val Class: C) where R: CharRace, C: CharClass {

    // stats
    var stats = Stats(Race.stats + Class.stats)
    var hp = stats.maxHp()
    var mp = stats.maxMp()

    // equipment
    var weapon: Weapon = when(Class){
        is Mage -> BasicStaff()
        is Warrior -> BasicSword()
        is Rogue -> BasicDagger()
        else -> BasicSword()
    }

    var armor: Armor = when(Class){
        is Mage -> BasicRobe()
        else -> BasicArmor()
    }

    // inventory
    var inventory = arrayOf<Item>()


    fun dealDamage(): Int {
        val modifier = when(weapon.damageType){
            0 -> stats.physDamageModifier()
            1 -> stats.magicDamageModifier()
            else -> 1.0f
        }
        return(weapon.getDamage(modifier))
    }

    fun receiveDamage(damage: Int, type: Int){
        val modifier = when(type){
            0 -> stats.physDefenceModifier()
            1 -> stats.magicDefenceModifier()
            else -> 1.0f
        }
        hp -= armor.reducedDmg(damage, modifier, type)
        if(hp < 0) {
            hp = 0
        }
    }

    fun isDead(): Boolean {
        return (hp <= 0)
    }
}