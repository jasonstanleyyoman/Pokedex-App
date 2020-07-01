package com.jason.first.coba_coba

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class ListPokemonAdapter(val context: Context, private val pokemons:Pokemons): RecyclerView.Adapter<ListPokemonAdapter.PokemonViewHolder>() {
    private val allPokemon = pokemons.allPokemon
    private val assetPath =  "file:///android_asset/PokemonImages/"
    private lateinit var onItemClickCallback:OnItemClickCallback

    inner class PokemonViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var pokemonNumber:TextView = itemView.findViewById(R.id.number)
        var pokemonName:TextView = itemView.findViewById(R.id.pokemon_name)
        var type1:TextView = itemView.findViewById(R.id.type1)
        var type2:TextView = itemView.findViewById(R.id.type2)
        var pokemonImage:ImageView = itemView.findViewById(R.id.pokemon_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_row,parent,false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.allPokemon.size
    }


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = allPokemon[position]

        holder.pokemonName.text = pokemon.name.capitalize()
        holder.pokemonNumber.text = if((position+1)/10 == 0) "#00"+(position+1).toString() else if ((position+1)/100==0) "#0"+(position+1).toString() else "#"+(position+1).toString()




        if(pokemon.types.count() == 2){

            holder.type1.visibility = View.VISIBLE
            holder.type2.visibility = View.VISIBLE


            holder.type1.text = pokemon.types[0].capitalize()
            val border : ShapeDrawable = ShapeDrawable(RectShape())
            border.paint.color = Color.parseColor(Utils.TypeToColor.getValue(pokemon.types[0]))
            border.paint.strokeWidth = 10f
            border.paint.style = Paint.Style.STROKE
            holder.type1.background = border

            holder.type2.text = pokemon.types[1].capitalize()
            val border2:ShapeDrawable = ShapeDrawable(RectShape())
            border2.paint.color = Color.parseColor(Utils.TypeToColor.getValue(pokemon.types[1]))
            border2.paint.strokeWidth = 10f
            border2.paint.style = Paint.Style.STROKE
            holder.type2.background = border2
        }else{

            holder.type1.visibility = View.VISIBLE
            holder.type2.visibility = View.INVISIBLE

            holder.type1.text = pokemon.types[0].capitalize()
            val border : ShapeDrawable = ShapeDrawable(RectShape())
            border.paint.color = Color.parseColor(Utils.TypeToColor.getValue(pokemon.types[0]))
            border.paint.strokeWidth = 10f
            border.paint.style = Paint.Style.STROKE
            holder.type1.background = border
        }
        Glide.with(holder.itemView.context).load(Uri.parse(assetPath+(position+1).toString()+".png")).into(holder.pokemonImage)


        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(allPokemon[holder.adapterPosition],holder.adapterPosition)}



    }
    fun setOnItemClickCallback(onItemClickCallback:OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback{
        fun onItemClicked(data:Pokemon,index:Int)
    }

}