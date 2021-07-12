package com.example.projetofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.projetofinal.bd.AppDatabase
import com.example.projetofinal.dao.UsuarioDAO
import com.example.projetofinal.model.Usuario
import com.example.projetofinal.model.UsuarioAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegisterActivity : AppCompatActivity() {


    lateinit var usuarioDAO: UsuarioDAO
    lateinit var usuarioAdapter: UsuarioAdapter

    // Váriaveis
    lateinit var etNomeRegistro: EditText
    lateinit var etEmailRegistro: EditText
    lateinit var etSenhaRegistro: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNomeRegistro = findViewById<EditText>(R.id.etNomeRegistro)
        etEmailRegistro = findViewById<EditText>(R.id.etEmailRegistro)
        etSenhaRegistro = findViewById<EditText>(R.id.etSenhaRegistro)

        //Instanciando o UsuarioDAO e criando o banco
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "teste_db").build()
        usuarioDAO = db.usuarioDAO()
        Log.i("Teste acesso: ","Entrou no OnCreate!")

        val rv = findViewById<RecyclerView>(R.id.rv_usuarios)
        usuarioAdapter = UsuarioAdapter()
        rv.adapter = usuarioAdapter
        rv.layoutManager = LinearLayoutManager(this)

        load()

    }

    fun load(){
        GlobalScope.launch(Dispatchers.Main){
            val usuarios = withContext(Dispatchers.IO){
                usuarioDAO.findAll()
            }
            usuarioAdapter.items = usuarios
        }
    }


    fun inserir(view: View) {
        val u = Usuario(etNomeRegistro.text.toString(), etEmailRegistro.text.toString(), etSenhaRegistro.text.toString())
        GlobalScope.launch(Dispatchers.Main){
            val usuarios = withContext(Dispatchers.IO){
                usuarioDAO.insert(u)
                usuarioDAO.findAll()
            }
            usuarioAdapter.items = usuarios
        }
        //Toast.makeText(this,"Usuário: ${u}",Toast.LENGTH_LONG).show()
        Toast.makeText(this,"Usuário inserido com sucesso!",Toast.LENGTH_SHORT).show()
    }
}