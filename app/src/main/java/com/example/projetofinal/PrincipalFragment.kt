package com.example.projetofinal

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.projetofinal.bd.AppDatabase
import com.example.projetofinal.dao.ViagemDAO
import com.example.projetofinal.model.ViagemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrincipalFragment : Fragment() {

    private lateinit var ctx: Context
    lateinit var viagemDAO : ViagemDAO
    lateinit var viagemAdapter: ViagemAdapter
    lateinit var rv : RecyclerView
    var user_id: Int = 0

    @SuppressLint("ResourceType")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        this.ctx = container?.context!!

        user_id = arguments?.getInt("user")!!
        Log.i("Principal Args:", "${user_id}")



        val db = Room.databaseBuilder(this.requireContext(), AppDatabase::class.java, "teste_db").build()
        viagemDAO = db.viagemDAO()
        viagemAdapter = ViagemAdapter()
        val view = inflater.inflate(R.layout.fragment_principal, container, false)
        load(view)

        // TODO
        // Não funcionando chamada no click do RV ainda, apesar de não ser isso
        rv.setOnClickListener {
            val intent = Intent(ctx, RegisterActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    @SuppressLint("ResourceType")
    fun load(view: View){
        rv = view.findViewById<RecyclerView>(R.id.rv_viagens)
        GlobalScope.launch(Dispatchers.Main){
            val viagens = withContext(Dispatchers.IO){
                viagemDAO.findById(user_id)
            }
            Log.i("Viagens:", "${viagens}")
            viagemAdapter.items = viagens
            rv.adapter = viagemAdapter
            rv.layoutManager = LinearLayoutManager(view.context)
        }
    }



}