package com.example.projetofinal

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.projetofinal.bd.AppDatabase
import com.example.projetofinal.dao.ViagemDAO
import com.example.projetofinal.model.Viagem
import com.example.projetofinal.model.ViagemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ViagemFragment() : Fragment() {

    lateinit var etDestinoViagem : EditText
    lateinit var etDtChegadaViagem : EditText
    lateinit var etDtPartidaViagem : EditText
    lateinit var etOrcamenteViagem : EditText
    lateinit var rbLazer : RadioButton
    lateinit var rbNegocios : RadioButton
    lateinit var rgOptions : RadioGroup
    lateinit var radioValue : String
    var user_id: Int = 0
    lateinit var viagemDAO: ViagemDAO
    lateinit var viagemAdapter: ViagemAdapter
    lateinit var btCadastraViagem : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user_id = arguments?.getInt("user")!!
        Log.i("Viagem Args:", "${user_id}")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viagem, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btCadastraViagem).setOnClickListener {
            inserirViagem(view)
            Log.i("Clique do botão: ", "Entrou")
        }
    }

    fun validaOptions() : String {
        if (rbLazer.isChecked){
            return "Lazer"
        } else {
            return "Negócios"
        }
    }

    fun inserirViagem(view: View){
        val db = Room.databaseBuilder(view.context, AppDatabase::class.java, "teste_db").build()
        viagemDAO = db.viagemDAO()


        etDestinoViagem = view.findViewById(R.id.etDestinoViagem)
        etDtChegadaViagem = view.findViewById(R.id.etDtChegadaViagem)
        etDtPartidaViagem = view.findViewById(R.id.etDtPartidaViagem)
        rbLazer = view.findViewById(R.id.rbLazer)
        etOrcamenteViagem = view.findViewById(R.id.etOrcamenteViagem)

        val v = Viagem(etDestinoViagem.text.toString(),
                validaOptions(),
                etDtChegadaViagem.text.toString(),
                etDtPartidaViagem.text.toString(),
                etOrcamenteViagem.text.toString().toDouble(),
                user_id
        )

        GlobalScope.launch(Dispatchers.Main){
            val viagens = withContext(Dispatchers.IO){
                viagemDAO.insert(v)
                viagemDAO.findAll()
            }
            viagemAdapter.items = viagens
        }
        Toast.makeText(context,"Viagem inserida com sucesso!", Toast.LENGTH_SHORT).show()
        Log.i("Objeto viagem: ", "${v}")
    }
}

