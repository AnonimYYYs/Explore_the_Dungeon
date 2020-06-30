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

        textView2.text = """
            |ur hp is ${player.hp}
            |mob hp is ${mob.hp}
        """.trimMargin()




    }

    fun dealDmg(view: View) {
        val z = player.dealDamage()
        val x = mob.dealDamage()
        player.receivePhysDamage(x)
        mob.receivePhysDamage(z)
        inventory_list.text = """
            |ur dmg is ${z}
            |mob dmg is ${x}
        """.trimMargin()
        textView2.text = """
            |ur hp is ${player.hp}
            |mob hp is ${mob.hp}
        """.trimMargin()
        textView3.text = """
            |ur char is ${player.Race.name} and ${player.Class.name}.
            |mob char is ${mob.Race.name} and ${mob.Class.name}.
            |ur char wears ${player.weapon.name} and ${player.armor.name}.
            |mob char wears ${mob.weapon.name} and ${mob.armor.name}.
        """.trimMargin()

        if(mob.isDead()){
            mob = newRandomMob()
        }
        if(player.isDead()){
            player.hp = 1000
        }
    }

}
