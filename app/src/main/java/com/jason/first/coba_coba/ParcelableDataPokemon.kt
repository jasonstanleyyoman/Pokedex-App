package com.jason.first.coba_coba

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelableDataPokemon(
    var number:Int,
    var name:String,
    var types:Array<String>,
    var description:String,
    var weight:Int,
    var height:Int,
    var hp:Int,
    var atk:Int,
    var def:Int,
    var sp_atk:Int,
    var sp_def:Int,
    var speed:Int
):Parcelable