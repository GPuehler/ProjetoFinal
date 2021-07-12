package com.example.projetofinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(val nome: String, val email: String, val senha: String){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}