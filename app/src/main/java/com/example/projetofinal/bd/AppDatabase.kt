package com.example.projetofinal.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projetofinal.dao.UsuarioDAO
import com.example.projetofinal.dao.ViagemDAO
import com.example.projetofinal.model.Usuario
import com.example.projetofinal.model.Viagem

@Database(entities = arrayOf(Usuario::class,Viagem::class), version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDAO() : UsuarioDAO
    abstract fun viagemDAO() : ViagemDAO

}