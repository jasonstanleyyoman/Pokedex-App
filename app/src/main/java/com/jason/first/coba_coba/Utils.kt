package com.jason.first.coba_coba



object Utils{

    private const val maxScore:Int = 100
    private const val minScore:Int = 20
    fun ToColor(score:Int): String {
        if(score >= maxScore){
            return "#00FF00"
        }else if(score <= minScore){
            return "#FF0000"
        }else{
            val green:Int = 255 * (score - minScore) / (maxScore-minScore)
            val red:Int = 255 * (maxScore-minScore-(score- minScore))/(maxScore-minScore)

            var redHex:String = java.lang.Integer.toHexString(red)
            redHex = if(redHex.length == 1) "0"+redHex else redHex
            println("RedHex"+redHex)
            var greenHex:String = java.lang.Integer.toHexString(green)
            greenHex = if(greenHex.length == 1) "0"+greenHex else greenHex
            println("GreenHex"+greenHex)
            return "#"+redHex+greenHex+"00"

        }
    }
    public val TypeToColor:Map<String,String> = mapOf(
        "bug" to "#A8B820",
        "dark" to "#705848",
        "dragon" to "#7038F8",
        "electric" to "#F8D030",
        "fighting" to "#C03028",
        "fire" to "#F08030",
        "flying" to "#A890F0",
        "ghost" to "#705898",
        "grass" to "#78C850",
        "ground" to "#E0C068",
        "ice" to "#98D8D8",
        "normal" to "#A8A878",
        "poison" to "#A040A0",
        "psychic" to "#F85888",
        "rock" to "#B8A038",
        "steel" to "#B8B8D0",
        "water" to "#6890F0",
        "fairy" to "#EE99AC"
    )
}
