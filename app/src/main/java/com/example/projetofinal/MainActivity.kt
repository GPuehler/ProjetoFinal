package com.example.projetofinal

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.projetofinal.bd.AppDatabase
import com.example.projetofinal.dao.UsuarioDAO
import com.example.projetofinal.model.Usuario
import com.example.projetofinal.model.UsuarioAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var usuarioDAO: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuração do banco de dados
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "teste_db").build()
        usuarioDAO = db.usuarioDAO()

        val btRegister = findViewById<Button>(R.id.btRegister)
        val btLogin = findViewById<Button>(R.id.btLogin)
        var etSenhaLogin = findViewById<EditText>(R.id.etSenhaLogin)
        var etEmailLogin = findViewById<EditText>(R.id.etEmailLogin)


        fun validaUser(email : String, senha : String){
            GlobalScope.launch(Dispatchers.Main){
                val user = withContext(Dispatchers.IO){
                    usuarioDAO.findLogin(email, senha)
                }
                if (user == null){
                    Toast.makeText(this@MainActivity, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Acesso liberado!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, MenuActivity::class.java)
                    intent.putExtra("user_id", user.id)
                    startActivity(intent)
                }
            }
        }

        btRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        btLogin.setOnClickListener{
            validaUser(etEmailLogin.text.toString(), etSenhaLogin.text.toString())
        }
    }


}