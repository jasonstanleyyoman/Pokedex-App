package com.jason.first.coba_coba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var rvPokemon:RecyclerView
    val gson = GsonBuilder().create()

    var pokemons = Pokemons(arrayListOf(Pokemon("","","", arrayOf("",""),Stats(1,1,1,1,1,1))))
    var weightsHeights = AllWeightAndHeight(arrayListOf(WeightAndHeight(1,1)))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createPokedex()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode:Int){
        when(selectedMode){
            R.id.account->{
                val moveIntent = Intent(this@MainActivity,account_detail::class.java)
                startActivity(moveIntent)
            }
        }
    }
    private fun createPokedex(){
        val pokemonString:String = applicationContext.assets.open("data.json").bufferedReader().use {
            it.readText()
        }
        val weightheightString = applicationContext.assets.open("weight.json").bufferedReader().use{
            it.readText()
        }
        pokemons = gson.fromJson(pokemonString,Pokemons::class.java)
        weightsHeights = gson.fromJson(weightheightString,AllWeightAndHeight::class.java)
        rvPokemon = findViewById(R.id.rv_pokemons)
        rvPokemon.setHasFixedSize(true)
        rvPokemon.layoutManager = LinearLayoutManager(this)
        val pokemonListAdapter = ListPokemonAdapter(this,pokemons)
        rvPokemon.adapter = pokemonListAdapter

        pokemonListAdapter.setOnItemClickCallback(object: ListPokemonAdapter.OnItemClickCallback{
            override fun onItemClicked(data:Pokemon,index:Int){
                moveToDetail(data,index)
            }
        })

    }
    private fun moveToDetail(pokemon:Pokemon,index:Int){
        val moveIntent = Intent(this@MainActivity,PokemonDetail::class.java)
        val movingPokemon = pokemons.allPokemon[index]
        val heightWeightOfPokemon = weightsHeights.allWeightAndHeight[index]
        val parcelablePokemon = ParcelableDataPokemon(
            index+1,
            movingPokemon.name,
            movingPokemon.types,
            movingPokemon.description,
            heightWeightOfPokemon.weight,
            heightWeightOfPokemon.height,
            movingPokemon.allStats.hp,
            movingPokemon.allStats.attack,
            movingPokemon.allStats.defense,
            movingPokemon.allStats.specialAttack,
            movingPokemon.allStats.specialDefense,
            movingPokemon.allStats.speed
        )
        moveIntent.putExtra("EXTRA_POKEMON",parcelablePokemon)
        startActivity(moveIntent)
    }
}
