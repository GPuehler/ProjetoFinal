package com.example.projetofinal.dao

import android.os.CpuUsageInfo
import androidx.room.*
import com.example.projetofinal.model.Usuario

@Dao
interface UsuarioDAO{

    @Insert()
    fun insert(usuario: Usuario)

    @Update
    fun udpate(usuario: Usuario)

    @Delete
    fun delete(usuario: Usuario)

    @Query("SELECT * FROM usuario ORDER BY nome")
    fun findAll(): List<Usuario>

    @Query("SELECT * FROM usuario WHERE id = :id")
    fun findById(id: Int): Usuario

    @Query("SELECT * FROM usuario WHERE email = :email and senha = :senha ")
    fun findLogin(email : String, senha : String): Usuario
}