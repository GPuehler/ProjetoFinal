package com.example.projetofinal.dao

import androidx.room.*
import com.example.projetofinal.model.Viagem

@Dao
interface ViagemDAO {

    @Insert
    fun insert(viagem: Viagem)

    @Update
    fun update(viagem: Viagem)

    @Delete
    fun delete(viagem: Viagem)

    @Query("SELECT * FROM viagem ORDER BY dtChegada DESC")
    fun findAll(): List<Viagem>

    @Query("SELECT * FROM viagem WHERE id_user = :id_user ORDER BY dtChegada DESC")
    fun findById(id_user: Int) : List<Viagem>

    @Query("SELECT * FROM viagem WHERE id = :id_viagem")
    fun findSpecific(id_viagem: Int) : Viagem
}