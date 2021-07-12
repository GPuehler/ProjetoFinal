package com.example.projetofinal.model


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetofinal.R
import org.w3c.dom.Text

class ViagemAdapter : RecyclerView.Adapter<ViagemAdapter.ViewHolder>() {

    var items = emptyList<Viagem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun teste(){

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var iv_TipoViagemIcon : ImageView
        val tv_Destino: TextView
        val tv_Chegada: TextView
        val tv_Partida: TextView
        val tv_TotalGasto: TextView

        init {
            iv_TipoViagemIcon = view.findViewById(R.id.iv_TipoViagemIcon)
            tv_Destino = view.findViewById(R.id.tv_Destino)
            tv_Chegada = view.findViewById(R.id.tv_Chegada)
            tv_Partida = view.findViewById(R.id.tv_Partida)
            tv_TotalGasto = view.findViewById(R.id.tv_TotalGasto)
        }

        fun bind(c: Viagem){

            if (c.tipo == "Lazer") {
                iv_TipoViagemIcon.setImageResource(R.drawable.praia_icon)
            }
            if (c.tipo == "Negócios") {
                iv_TipoViagemIcon.setImageResource(R.drawable.trabalho_icon)
            }
            //iv_TipoViagemIcon. = c.tipo
            tv_Destino.text = ("Destino: ${c.destino}")
            tv_Chegada.text = ("Data chegada: ${c.dtChegada}")
            tv_Partida.text = ("Data partida: ${c.dtPartida}")
            tv_TotalGasto.text = ("Orçamento: ${c.orcamento.toString()}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViagemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viagem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}