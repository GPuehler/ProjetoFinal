package com.example.projetofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.projetofinal.bd.AppDatabase
import com.example.projetofinal.dao.ViagemDAO
import com.example.projetofinal.model.ViagemAdapter
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class MenuActivity : AppCompatActivity() {

    lateinit var viagemDAO : ViagemDAO
    lateinit var viagemAdapter: ViagemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_principal, PrincipalFragment())
                .commit()


        /*
        val fabNovaViagem = findViewById<FloatingActionButton>(R.id.fabNovaViagem)
        val menuOptions = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val id_user : Int = intent.getIntExtra("user_id",1)
        val rv = findViewById<RecyclerView>(R.id.rv_viagens)
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "teste_db").build()
        viagemDAO = db.viagemDAO()
        viagemAdapter = ViagemAdapter()
        */
        val fabNovaViagem = findViewById<FloatingActionButton>(R.id.fabNovaViagem)
        val menuOptions = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val id_user : Int = intent.getIntExtra("user_id",1)

        fabNovaViagem.setOnClickListener {
            createFragment(ViagemFragment(id_user))
        }

        menuOptions.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.ic_novaviagem -> createFragment(ViagemFragment(id_user))
                R.id.ic_home -> createFragment(PrincipalFragment())
                R.id.ic_ajuda -> suplica("Não disponível ainda!")
            }
        }

        /*
        rv.adapter = viagemAdapter
        rv.layoutManager = LinearLayoutManager(this)
        load()
         */
    }

    private fun createFragment(fragment: Fragment): Boolean {
        Log.i("CreateFragment Entrou", "${fragment}")
        //Teste retirando as viagens de segnund plano
        //viagemAdapter.items = emptyList()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_principal, fragment)
                .addToBackStack(null)
                .commit()
        return true
    }

    fun load(){
        GlobalScope.launch(Dispatchers.Main){
            val viagens = withContext(Dispatchers.IO){
                viagemDAO.findAll()
            }
            viagemAdapter.items = viagens
        }
    }

    fun suplica(msg : String){
        Toast.makeText(this,"${msg}",Toast.LENGTH_SHORT).show()
    }
}