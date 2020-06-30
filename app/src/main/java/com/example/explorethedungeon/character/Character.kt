package com.example.explorethedungeon.character

import com.example.explorethedungeon.items.*

fun newRandomMob(): Character<CharRace, CharClass> {
    val mobRace = getCharRace((raceList).random())
    val mobClass = getCharClass((classList).random())
    return Character(mobRace,mobClass)
}

class Character<R, C>(val Race: R, val Class: C) where R: CharRace, C: CharClass {

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

    var hp = Race.maxHp

    var inventory = arrayOf<Item>(BasicStaff(), IronSword(), BasicShirt())

    fun dealDamage(): Int {
        return(weapon.getDamage(Race.physicDamageModifier * Class.physicDamageModifier))
    }

    fun receivePhysDamage(damage: Int){
        hp -= (damage * (1 - armor.physDef)).toInt()
        if(hp < 0) {
            hp = 0
        }
    }

    fun isDead(): Boolean {
        return (hp <= 0)
    }
}