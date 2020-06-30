package com.example.explorethedungeon.items

class BasicSword : Weapon {
    override val name = "Basic Sword"
    override val description = "simple basic wooden sword"
    override val damageType = 0
    override val damageMin = 10
    override val damageRate = 2
}

class IronSword : Weapon {
    override val name = "Iron Sword"
    override val description = "sword from iron"
    override val damageType = 0
    override val damageMin = 11
    override val damageRate = 5
}

class BasicStaff : Weapon {
    override val name = "Basic Staff"
    override val description = "simple wooden staff"
    override val damageType = 1
    override val damageMin = 5
    override val damageRate = 10
}

class BasicDagger : Weapon {
    override val name = "Basic Dagger"
    override val description = "just half of simple sword"
    override val damageType = 0
    override val damageMin = 9
    override val damageRate = 1
}