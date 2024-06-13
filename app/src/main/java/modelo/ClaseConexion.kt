package modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {
    fun cadenaConexion(): Connection? {
        try {
            val url = "jdbc:oracle:thin:@192.168.56.1:1521:xe"
            val usuario = "c##_rodrigo"
            val contrasena = "rodrigo"

            val connection = DriverManager.getConnection(url, usuario, contrasena)
            return connection
        } catch (e: Exception) {
            println("Este es el error en la cadena de conexion: $e")
            return null
        }
    }
}