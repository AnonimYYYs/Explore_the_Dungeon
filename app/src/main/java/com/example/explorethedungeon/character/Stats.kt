package com.example.explorethedungeon.character

data class RawStats (
    var strength: Int, // сила
    var vitality: Int, // жизненность
    var agility: Int, // ловкость
    var wisdom: Int, // мозги
    var magika: Int // манность
){
    operator fun plus(toSum: RawStats): RawStats{
        return RawStats(
            strength + toSum.strength,
            vitality + toSum.vitality,
            agility + toSum.agility,
            wisdom + toSum.wisdom,
            magika + toSum.magika)
    }

    fun roundToMax(){
        if (strength > maxStats.strength) {strength = maxStats.strength}
        if (vitality > maxStats.vitality) {vitality = maxStats.vitality}
        if (agility > maxStats.agility) {agility = maxStats.agility}
        if (wisdom > maxStats.wisdom) {wisdom = maxStats.wisdom}
        if (magika > maxStats.magika) {magika = maxStats.magika}
    }
}

val maxStats = RawStats(100, 100, 100, 100, 100)

class Stats (var rawStats: RawStats) {
    fun maxHp(): Int{
        return( rawStats.vitality * 10 )
    }

    fun maxMp(): Int {
        return( rawStats.magika * 15 )
    }

    fun physDamageModifier(): Float {
        return (1.0f + rawStats.strength / maxStats.strength)
    }

    fun magicDamageModifier(): Float {
        return( 1.0f + rawStats.wisdom / maxStats.wisdom + rawStats.magika / (2 * maxStats.magika) )
    }

    fun physDefenceModifier(): Float {
        return( 1.0f + 0.1f * (rawStats.strength / maxStats.strength) + 0.4f * (rawStats.vitality / maxStats.vitality))
    }

    fun magicDefenceModifier(): Float {
        return( 1.0f + 0.2f * (rawStats.wisdom / maxStats.wisdom) + 0.3f * (rawStats.magika / maxStats.magika))
    }

    fun modifyStats(deltaStats: RawStats){
        rawStats += deltaStats
        rawStats.roundToMax()
    }
}