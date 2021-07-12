package com.example.projetofinal.model


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetofinal.R
import org.w3c.dom.Text

class GastosAdapter : RecyclerView.Adapter<GastosAdapter.ViewHolder>() {

    var items = emptyList<Gastos>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //TODO

        var tv_DescGastoValue: TextView
        var tv_ValorGastoValue: TextView

        init {
            tv_DescGastoValue = view.findViewById(R.id.tv_DescGastoValue)
            tv_ValorGastoValue = view.findViewById(R.id.tv_ValorGastoValue)
        }

        fun bind(g: Gastos){
            tv_DescGastoValue.text = ("Destino: ${g.desc}")
            tv_ValorGastoValue.text = ("Data chegada: ${g.valor}")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastosAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gastos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}