package com.example.retodam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SolicitudAdapter(
    private var listaSolicitudes: MutableList<Solicitud>,
    private val onCancelClick: (Solicitud) -> Unit
) : RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_solicitud, parent, false)
        return SolicitudViewHolder(view)
    }

    override fun onBindViewHolder(holder: SolicitudViewHolder, position: Int) {
        holder.bind(listaSolicitudes[position])
    }

    override fun getItemCount(): Int = listaSolicitudes.size

    inner class SolicitudViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textVacante = itemView.findViewById<TextView>(R.id.textVacante)
        private val textEstado = itemView.findViewById<TextView>(R.id.textEstado)
        private val buttonCancelar = itemView.findViewById<Button>(R.id.buttonCancelar)

        fun bind(solicitud: Solicitud) {
            textVacante.text = "Vacante: ${solicitud.idVacante}"
            textEstado.text = "Estado: ${solicitud.estado}"

            buttonCancelar.setOnClickListener {
                onCancelClick(solicitud)
            }
        }
    }
}
