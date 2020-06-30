package com.example.explorethedungeon

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.explorethedungeon.character.*
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    lateinit var player: Character<CharRace, CharClass>
    lateinit var mob: Character<CharRace, CharClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // set player class
        val playerClass = getCharClass(intent.getStringExtra("Class"))


        // set player race
        val playerRace = getCharRace(intent.getStringExtra("Race"))


        if(playerClass is NullClass || playerRace is NullRace){
            finish()
        }
        player = Character(playerRace, playerClass)
        mob = newRandomMob()

        enemy_label.text = "enemy is ${mob.Race.name} and ${mob.Class.name}"
        enemy_hp.text = "hp: ${mob.hp}/${mob.stats.maxHp()}"
        enemy_weapon.text = "weapon is ${mob.weapon.name} (${mob.weapon.description})"
        enemy_armor.text = "armor is ${mob.armor.name} (${mob.armor.description})"

        player_label.text = "player is ${player.Race.name} and ${player.Class.name}"
        player_hp.text = "hp: ${player.hp}/${player.stats.maxHp()}"
        player_weapon.text = "weapon is ${player.weapon.name} (${player.weapon.description})"
        player_armor.text = "armor is ${player.armor.name} (${player.armor.description})"
    }

    fun onDD(view: View) {
        val playerDamage = player.dealDamage()

        val mobDamage = player.dealDamage()

        damage_log.text = """
            |ur damage is ${playerDamage} and type is ${stringType(player.weapon.damageType)}
            |mob damage is ${mobDamage} and type is ${stringType(mob.weapon.damageType)}
        """.trimMargin()

        mob.receiveDamage(playerDamage, player.weapon.damageType)
        player.receiveDamage(mobDamage, mob.weapon.damageType)

        if(mob.isDead()){
            damage_log.text = """
                ${damage_log.text}
                mob is dead woohoo!!!
            """.trimMargin()
            newMob()
        }

        if(player.isDead()){
            damage_log.text = """
                ${damage_log.text}
                ur fully healed.....
            """.trimMargin()
            player.hp = player.stats.maxHp()
        }

        enemy_hp.text = "hp: ${mob.hp}/${mob.stats.maxHp()}"
        player_hp.text = "hp: ${player.hp}/${player.stats.maxHp()}"
    }

    fun stringType(x: Int): String {
        return when(x){
            0 -> ("phys")
            1 -> ("mag")
            else -> ("")
        }
    }

    fun newMob(){
        mob = newRandomMob()
        enemy_label.text = "enemy is ${mob.Race.name} and ${mob.Class.name}"
        enemy_hp.text = "hp: ${mob.hp}/${mob.stats.maxHp()}"
        enemy_weapon.text = "weapon is ${mob.weapon.name} (${mob.weapon.description})"
        enemy_armor.text = "armor is ${mob.armor.name} (${mob.armor.description})"
    }

}
