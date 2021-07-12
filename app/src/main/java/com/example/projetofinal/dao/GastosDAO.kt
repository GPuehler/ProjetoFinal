package com.example.projetofinal.dao

import androidx.room.*
import com.example.projetofinal.model.Gastos
import com.example.projetofinal.model.Viagem

@Dao
interface GastosDAO {

    @Insert
    fun insert(gastos: Gastos)

    @Update
    fun update(gastos: Gastos)

    @Delete
    fun delete(gastos: Gastos)

    @Query("SELECT * FROM gastos ORDER BY valor DESC")
    fun findAll(): List<Gastos>

    @Query("SELECT * FROM gastos WHERE id_viagem = :id_viagem ORDER BY valor DESC")
    fun findById(id_viagem: Int) : Gastos
}