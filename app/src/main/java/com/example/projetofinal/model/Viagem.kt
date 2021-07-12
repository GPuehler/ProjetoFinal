package com.example.projetofinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Viagem(val destino: String,
                  var tipo: String,
                  val dtChegada: String,
                  val dtPartida: String,
                  val orcamento: Double,
                  val id_user: Int){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
