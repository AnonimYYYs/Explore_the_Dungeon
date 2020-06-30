package com.example.explorethedungeon

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.explorethedungeon.character.classList
import com.example.explorethedungeon.character.raceList
import kotlinx.android.synthetic.main.activity_choose_character.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // ---------------- some val and var --------------

    private val requestCodeChooseChar = 0

    // ----------------------- overriding -------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // доступность рас для выбора
        val openRaces = getSharedPreferences("OpenRaces", Context.MODE_PRIVATE)
        openRaces.edit().putBoolean("Human", true).apply()  // всегда доступно
        // доступность классов для выбора
        val openClasses = getSharedPreferences("OpenClasses", Context.MODE_PRIVATE)
        openClasses.edit().putBoolean("Warrior", true).apply()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // мы выбрали чара, теперь запуск игры
        if(requestCode == requestCodeChooseChar){
            var charRace: String?
            var charClass: String? = null
            if(data != null){
                if(resultCode == Activity.RESULT_OK){
                    charRace = data.getStringExtra("Race")
                    charClass = data.getStringExtra("Class")
                    if(charRace != null && charClass != null){
                        val gameIntent = Intent(this, GameActivity::class.java)
                        gameIntent.putExtra("Race", charRace)
                        gameIntent.putExtra("Class", charClass)
                        startActivity(gameIntent)
                    }
                }

            }
        }
    }

    // ------------------- buttons --------------

    fun toStartNewGame(view: View) {
        val newCharIntent = Intent(this, ChooseActivity::class.java)
        startActivityForResult(newCharIntent, requestCodeChooseChar)
    }

    fun toShowAboutGame(view: View) {}
}

// activity of choosing class and race of new player
class ChooseActivity : AppCompatActivity(){


    private var chosen = ""
    // ------------- all races to choose ---------

    private var chosenRace = ""

    // ------------ all classes to choose ----------

    private var chosenClass = ""

    // --------------- overriding -----------------
    private lateinit var chooseRaceAdapter :ArrayAdapter<String>
    private lateinit var chooseClassAdapter :ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_character)

        // подключаемся к "сохраненному"
        val openRaces = getSharedPreferences("OpenRaces", Context.MODE_PRIVATE)
        val openClasses = getSharedPreferences("OpenClasses", Context.MODE_PRIVATE)
        // создаем листы, в котором буду доступные расы классы
        val chooseRaceList = mutableListOf<String>()
        val chooseClassList = mutableListOf<String>()
        // заполняем листы
        for(race in raceList){
            if(openRaces.getBoolean(race, false)){
                chooseRaceList.add(race)
            }
        }
        for(cls in classList){
            if(openClasses.getBoolean(cls, false)){
                chooseClassList.add(cls)
            }
        }
        // создаем адаптеры
        chooseRaceAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, chooseRaceList)
        chooseClassAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, chooseClassList)

        // ставим адаптер
        list_view_choose.adapter = chooseRaceAdapter

        // выбираем расу
        list_view_choose.onItemClickListener =
            OnItemClickListener { _, view, _, _ ->
                chosen = (view as TextView).text.toString()
                button_choose.text = "choose $chosen"
            }



    }

    override fun finish() {
        val answerIntent = Intent()
        if(chosenRace != "") {
            answerIntent.putExtra("Race", chosenRace)
        }
        if(chosenClass != "") {
            answerIntent.putExtra("Class", chosenClass)
        }
        setResult(Activity.RESULT_OK, answerIntent)
        super.finish()
    }

    fun toMakeChoose(view: View) {
        if(chosen != "") {
            if (list_view_choose.adapter == chooseRaceAdapter) {
                chosenRace = chosen
                chosen = ""
                list_view_choose.adapter = chooseClassAdapter
                chooseClassAdapter.notifyDataSetChanged()
                button_choose.text = "choose class"
            } else if (list_view_choose.adapter == chooseClassAdapter) {
                chosenClass = chosen
                finish()
            }
        }
    }
}