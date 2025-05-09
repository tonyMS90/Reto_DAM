package com.example.retodam


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BuscarVacantesActivity : AppCompatActivity() {

    private lateinit var spinnerEmpresa: Spinner
    private lateinit var spinnerTipoContrato: Spinner
    private lateinit var spinnerCategoria: Spinner
    private lateinit var recyclerVacantes: RecyclerView
    private lateinit var vacanteAdapter: VacanteAdapter

    private var vacantesDisponibles = listOf<Vacante>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_vacantes)

        spinnerEmpresa = findViewById(R.id.spinnerEmpresa)
        spinnerTipoContrato = findViewById(R.id.spinnerTipoContrato)
        spinnerCategoria = findViewById(R.id.spinnerCategoria)
        recyclerVacantes = findViewById(R.id.recyclerVacantes)

        // Datos simulados
        vacantesDisponibles = VacantesSimuladas.listaVacantes.filter { it.estado == "CREADA" }

        // Configurar Spinners
        configurarFiltros()

        // Configurar RecyclerView
        vacanteAdapter = VacanteAdapter(vacantesDisponibles)
        recyclerVacantes.layoutManager = LinearLayoutManager(this)
        recyclerVacantes.adapter = vacanteAdapter

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Buscar Vacantes" // (opcional, puedes poner título)


    }

    private fun configurarFiltros() {

        val empresas = listOf("Todas") + vacantesDisponibles.map { it.empresa }.distinct()
        spinnerEmpresa.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, empresas)

        val tiposContrato = listOf("Todos") + vacantesDisponibles.map { it.tipoContrato }.distinct()
        spinnerTipoContrato.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposContrato)

        val categorias = listOf("Todas") + vacantesDisponibles.map { it.categoria }.distinct()
        spinnerCategoria.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias)

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                aplicarFiltros()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinnerEmpresa.onItemSelectedListener = listener
        spinnerTipoContrato.onItemSelectedListener = listener
        spinnerCategoria.onItemSelectedListener = listener
    }

    private fun aplicarFiltros() {
        var listaFiltrada = vacantesDisponibles

        val empresaSeleccionada = spinnerEmpresa.selectedItem.toString()
        if (empresaSeleccionada != "Todas") {
            listaFiltrada = listaFiltrada.filter { it.empresa == empresaSeleccionada }
        }

        val contratoSeleccionado = spinnerTipoContrato.selectedItem.toString()
        if (contratoSeleccionado != "Todos") {
            listaFiltrada = listaFiltrada.filter { it.tipoContrato == contratoSeleccionado }
        }

        val categoriaSeleccionada = spinnerCategoria.selectedItem.toString()
        if (categoriaSeleccionada != "Todas") {
            listaFiltrada = listaFiltrada.filter { it.categoria == categoriaSeleccionada }
        }

        vacanteAdapter = VacanteAdapter(listaFiltrada)
        recyclerVacantes.adapter = vacanteAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

