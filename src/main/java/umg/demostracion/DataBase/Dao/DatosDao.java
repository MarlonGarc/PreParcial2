package umg.demostracion.DataBase.Dao;

import umg.demostracion.DataBase.Conexion.Conexion;
import umg.demostracion.DataBase.Model.Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDao {

    private Connection conexion;

    // Constructor que recibe la conexión
    public DatosDao() throws SQLException {
        this.conexion = Conexion.getConnection();
    }

    // Método para crear un nuevo dato
    public void crearDato(Datos dato) throws SQLException {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, dato.getNombre());
        ps.setString(2, dato.getApellido());
        ps.setString(3, dato.getDepartamento());
        ps.setDate(4, dato.getFechaNacimiento());
        ps.executeUpdate();
    }

    // Método para leer un dato por código
    public Datos leerDato(int codigo) throws SQLException {
        String sql = "SELECT * FROM tb_datos WHERE codigo = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Datos(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("departamento"),
                    rs.getDate("fecha_nacimiento")
            );
        }
        return null;
    }

    // Método para actualizar un dato
    public void actualizarDato(Datos dato) throws SQLException {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, dato.getNombre());
        ps.setString(2, dato.getApellido());
        ps.setString(3, dato.getDepartamento());
        ps.setDate(4, dato.getFechaNacimiento());
        ps.setInt(5, dato.getCodigo());
        ps.executeUpdate();
    }

    // Método para eliminar un dato por código
    public void eliminarDato(int codigo) throws SQLException {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.executeUpdate();
    }

    // Método para obtener todos los datos
    public List<Datos> obtenerTodosLosDatos() throws SQLException {
        List<Datos> listaDatos = new ArrayList<>();
        String sql = "SELECT * FROM tb_datos";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            listaDatos.add(new Datos(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("departamento"),
                    rs.getDate("fecha_nacimiento")
            ));
        }
        return listaDatos;
    }
}
