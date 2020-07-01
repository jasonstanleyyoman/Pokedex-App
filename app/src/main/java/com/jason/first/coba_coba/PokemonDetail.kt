package com.jason.first.coba_coba

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import com.bumptech.glide.Glide
import kotlin.random.Random


class PokemonDetail : AppCompatActivity() {
    private val assetPath =  "file:///android_asset/PokemonImages/"
    private val assetPathNormal = "file:///android_asset/PokemonSprites/"
    private val assetPathShiny = "file:///android_asset/PokemonShinySprites/"
    companion object{
        const val EXTRA_POKEMON = "extra_pokemon"
    }
    fun Double.round(decimals:Int):Double{
        var multiplier = 1.0
        repeat(decimals){
            multiplier *= 10
        }
        return kotlin.math.round(this*multiplier)/multiplier
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        val pokemon = intent.getParcelableExtra<ParcelableDataPokemon>("EXTRA_POKEMON")


        val detailNumber:TextView = findViewById(R.id.detail_number)
        val detailImage:ImageView = findViewById(R.id.detail_image)
        val detailName:TextView = findViewById(R.id.detail_name)
        val detailType1:TextView = findViewById(R.id.detail_type1)
        val detailType2:TextView = findViewById(R.id.detail_type2)
        val detailDescription:TextView = findViewById(R.id.detail_description)
        val detailWeight:TextView = findViewById(R.id.detail_weight)
        val detailHeight:TextView = findViewById(R.id.detail_height)
        val hpNum:TextView = findViewById(R.id.hp_num)
        val atkNum:TextView = findViewById(R.id.atk_num)
        val defNum:TextView = findViewById(R.id.def_num)
        val spAtkNum:TextView = findViewById(R.id.sp_atk_num)
        val spDefNum:TextView = findViewById(R.id.sp_def_num)
        val speedNum:TextView = findViewById(R.id.speed_num)
        val normalImage:ImageView = findViewById(R.id.normal_image)
        val shinyImage:ImageView = findViewById(R.id.shiny_image)
        val parent:LinearLayout = findViewById(R.id.parent)
        val hpBar:TextView = findViewById(R.id.hp)
        val atkBar:TextView = findViewById(R.id.attack)
        val defBar:TextView = findViewById(R.id.defense)
        val spAtkBar:TextView = findViewById(R.id.sp_atk)
        val spDefBar:TextView = findViewById(R.id.sp_def)
        val speedBar:TextView = findViewById(R.id.speed)
        val playWithPokemonButton: Button = findViewById(R.id.play_with_pokemon)


        detailNumber.text = if((pokemon.number)/10 == 0) "#00"+(pokemon.number).toString() else if ((pokemon.number)/100==0) "#0"+(pokemon.number).toString() else "#"+(pokemon.number).toString()

        detailName.text = pokemon.name.capitalize()

        Glide.with(this).load(Uri.parse(assetPath+pokemon.number.toString()+".png")).into(detailImage)

        if(pokemon.types.count() == 2){

            detailType1.visibility = View.VISIBLE
            detailType2.visibility = View.VISIBLE


            detailType1.text = pokemon.types[0].capitalize()
            val border : ShapeDrawable = ShapeDrawable(RectShape())
            border.paint.color = Color.parseColor(Utils.TypeToColor.getValue(pokemon.types[0]))
            border.paint.strokeWidth = 10f
            border.paint.style = Paint.Style.STROKE
            detailType1.background = border

            detailType2.text = pokemon.types[1].capitalize()
            val border2: ShapeDrawable = ShapeDrawable(RectShape())
            border2.paint.color = Color.parseColor(Utils.TypeToColor.getValue(pokemon.types[1]))
            border2.paint.strokeWidth = 10f
            border2.paint.style = Paint.Style.STROKE
            detailType2.background = border2
        }else{

            detailType1.visibility = View.VISIBLE
            detailType2.visibility = View.INVISIBLE

            detailType1.text = pokemon.types[0].capitalize()
            val border : ShapeDrawable = ShapeDrawable(RectShape())
            border.paint.color = Color.parseColor(Utils.TypeToColor.getValue(pokemon.types[0]))
            border.paint.strokeWidth = 10f
            border.paint.style = Paint.Style.STROKE
            detailType1.background = border
        }

        detailDescription.text = pokemon.description

        detailWeight.text = "Weight : "+(pokemon.weight.toDouble()/10).round(2).toString() + "kg"

        detailHeight.text = "Height : " + (pokemon.height.toDouble()/10).round(2).toString() + "m"

        hpNum.text = pokemon.hp.toString()
        atkNum.text = pokemon.atk.toString()
        defNum.text = pokemon.def.toString()
        spAtkNum.text = pokemon.sp_atk.toString()
        spDefNum.text = pokemon.sp_def.toString()
        speedNum.text = pokemon.speed.toString()

        val normalObserver : ViewTreeObserver = normalImage.viewTreeObserver
        normalObserver.addOnGlobalLayoutListener(object:ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                normalImage.layoutParams.height = normalImage.width
            }
        })

        val shinyObserver : ViewTreeObserver = shinyImage.viewTreeObserver
        shinyObserver.addOnGlobalLayoutListener(object:ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                shinyImage.layoutParams.height = shinyImage.width
            }
        })

        Glide.with(this).load(Uri.parse(assetPathNormal+pokemon.number.toString()+".png")).into(normalImage)
        Glide.with(this).load(Uri.parse(assetPathShiny+pokemon.number.toString()+".png")).into(shinyImage)

        val parentWidthObserver:ViewTreeObserver =parent.viewTreeObserver
        parentWidthObserver.addOnGlobalLayoutListener(object:ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                var highest = pokemon.hp
                highest = if(pokemon.atk > highest) pokemon.atk else highest
                highest = if(pokemon.def > highest) pokemon.def else highest
                highest = if(pokemon.sp_atk > highest) pokemon.sp_atk else highest
                highest = if(pokemon.sp_def > highest) pokemon.sp_def else highest
                highest = if(pokemon.speed > highest) pokemon.speed else highest



                val barForHp:ShapeDrawable = ShapeDrawable(RectShape())
                val hpColor = Color.parseColor(Utils.ToColor(pokemon.hp))
                barForHp.paint.color = hpColor
                hpBar.layoutParams.width = ((pokemon.hp.toDouble() / highest) * parent.width).toInt()
                hpBar.background = barForHp

                val barForAtk:ShapeDrawable = ShapeDrawable(RectShape())
                val atkColor = Color.parseColor(Utils.ToColor(pokemon.atk))
                barForAtk.paint.color = atkColor
                atkBar.layoutParams.width = ((pokemon.atk.toDouble() / highest) * parent.width).toInt()
                atkBar.background = barForAtk

                val barForDef:ShapeDrawable = ShapeDrawable(RectShape())
                val defColor = Color.parseColor(Utils.ToColor(pokemon.def))
                barForDef.paint.color = defColor
                defBar.layoutParams.width = ((pokemon.def.toDouble() / highest) * parent.width).toInt()
                defBar.background = barForDef

                val barForSpAtk:ShapeDrawable = ShapeDrawable(RectShape())
                val spAtkColor = Color.parseColor(Utils.ToColor(pokemon.sp_atk))
                barForSpAtk.paint.color = spAtkColor
                spAtkBar.layoutParams.width = ((pokemon.sp_atk.toDouble() / highest) * parent.width).toInt()
                spAtkBar.background = barForSpAtk

                val barForSpDef:ShapeDrawable = ShapeDrawable(RectShape())
                val spDefColor = Color.parseColor(Utils.ToColor(pokemon.sp_def))
                barForSpDef.paint.color = spDefColor
                spDefBar.layoutParams.width = ((pokemon.sp_def.toDouble() / highest) * parent.width).toInt()
                spDefBar.background = barForSpDef

                val barForSpeed:ShapeDrawable = ShapeDrawable(RectShape())
                val speedColor = Color.parseColor(Utils.ToColor(pokemon.speed))
                barForSpeed.paint.color = speedColor
                speedBar.layoutParams.width = ((pokemon.speed.toDouble() / highest) * parent.width).toInt()
                speedBar.background = barForSpeed
            }
        })

        playWithPokemonButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View) {
                when(v.id){
                    R.id.play_with_pokemon->{
                        var randomIndex = Random.nextInt(0,PokemonWords.listWords.size)
                        if(randomIndex == PokemonWords.listWords.size){
                            randomIndex -= 1
                        }
                        val words = PokemonWords.listWords[randomIndex]

                        val newWords = words.replace("pokemonName",pokemon.name.capitalize())
                        println(newWords)
                        Toast.makeText(this@PokemonDetail,newWords,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

}
