package com.example.projetofinal.model


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetofinal.R

class UsuarioAdapter : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

    var items = emptyList<Usuario>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val tv_Nome : TextView
        val tv_Email: TextView
        val tv_Senha: TextView

        init {
            tv_Nome = view.findViewById(R.id.tv_Nome)
            tv_Email = view.findViewById(R.id.tv_Email)
            tv_Senha = view.findViewById(R.id.tv_Senha)
        }

        fun bind(c: Usuario){
            tv_Nome.text = c.nome
            tv_Email.text = c.email
            tv_Senha.text = c.senha
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usuarios, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}