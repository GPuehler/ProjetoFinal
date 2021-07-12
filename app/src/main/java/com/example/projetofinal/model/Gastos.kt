package com.example.projetofinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Gastos(val tipo: String,
                  var valor: Double,
                  val dtGasto: String,
                  val desc: String,
                  val local: String,
                  val id_viagem: Int){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
