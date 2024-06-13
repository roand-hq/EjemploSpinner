package bryan.miranda.ejemplospinner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.dataClassDoctores

class pacientes : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_pacientes, container, false)
        val spDoctores = vista.findViewById<Spinner>(R.id.spDoctores)
        CoroutineScope(Dispatchers.IO).launch {
            val listaDoctores = obtenerDoctores()
            val nombres = listaDoctores.map { it.nombreDoctor }
            withContext(Dispatchers.Main) {
                val adaptador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, nombres)
                spDoctores.adapter = adaptador

            }
        }


        return vista
    }

    private fun obtenerDoctores(): List<dataClassDoctores>{
        val objConexion = ClaseConexion().cadenaConexion()
        val statement = objConexion?.createStatement()!!
        val resultSet = statement.executeQuery("select * from tbDoctores")
        val lista = mutableListOf<dataClassDoctores>()
        while(resultSet.next()) {
            val UUID = resultSet.getString("DoctorUUID")
            val nombre = resultSet.getString("nombreDoctor")
            val Especialidad = resultSet.getString("Especialidad")
            val Telefono = resultSet.getString("Telefono")

            val valoresJuntos = dataClassDoctores(UUID, nombre, Especialidad, Telefono)
            lista.add(valoresJuntos)
        }
        return lista
    }
}