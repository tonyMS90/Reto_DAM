package com.example.retodam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VacanteAdapter(private val listaVacantes: List<Vacante>) : RecyclerView.Adapter<VacanteAdapter.VacanteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vacante, parent, false)
        return VacanteViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacanteViewHolder, position: Int) {
        val vacante = listaVacantes[position]
        holder.bind(vacante)
    }

    override fun getItemCount(): Int = listaVacantes.size

    class VacanteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titulo = itemView.findViewById<TextView>(R.id.textTitulo)
        private val empresa = itemView.findViewById<TextView>(R.id.textEmpresa)
        private val tipoContrato = itemView.findViewById<TextView>(R.id.textTipoContrato)

        fun bind(vacante: Vacante) {
            titulo.text = vacante.titulo
            empresa.text = vacante.empresa
            tipoContrato.text = vacante.tipoContrato

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = android.content.Intent(context, DetalleVacanteActivity::class.java)
                intent.putExtra("idVacante", vacante.id)
                intent.putExtra("titulo", vacante.titulo)
                intent.putExtra("empresa", vacante.empresa)
                intent.putExtra("tipoContrato", vacante.tipoContrato)
                intent.putExtra("descripcion", vacante.descripcion)
                context.startActivity(intent)
            }
        }
    }
}
