package umg.demostracion.DataBase.Dao;

import umg.demostracion.DataBase.Conexion.Conexion;
import umg.demostracion.DataBase.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao() {
        this.connection = Conexion.getConnection();
    }

    // Método para crear un nuevo usuario
    public void crearUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, usuario.getCarne());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getSeccion());
            ps.setLong(5, usuario.getTelegramId());
            ps.setString(6, usuario.getActivo());
            ps.executeUpdate();
        }
    }

    // Método para leer un usuario por correo
    public Usuario leerUsuarioPorCorreo(String correo) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE correo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setCarne(rs.getString("carne"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setSeccion(rs.getString("seccion"));
                usuario.setTelegramId(rs.getLong("telegramid"));
                usuario.setActivo(rs.getString("activo"));
                return usuario;
            }
        }
        return null;
    }

    // Método para leer un usuario por ID

    public Usuario leerUsuarioPorId(int id) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE idusuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setCarne(rs.getString("carne"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setSeccion(rs.getString("seccion"));
                usuario.setTelegramId(rs.getLong("telegramid"));
                usuario.setActivo(rs.getString("activo"));
                return usuario;
            }
        }
        return null;
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE tb_usuarios SET carne = ?, nombre = ?, seccion = ?, telegramid = ?, activo = ? WHERE correo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, usuario.getCarne());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getSeccion());
            ps.setLong(4, usuario.getTelegramId());
            ps.setString(5, usuario.getActivo());
            ps.setString(6, usuario.getCorreo());
            ps.executeUpdate();
        }
    }

    // Método para eliminar un usuario por correo
    public void eliminarUsuario(String correo) throws SQLException {
        String query = "DELETE FROM tb_usuarios WHERE correo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, correo);
            ps.executeUpdate();
        }
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM tb_usuarios";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setCarne(rs.getString("carne"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setSeccion(rs.getString("seccion"));
                usuario.setTelegramId(rs.getLong("telegramid"));
                usuario.setActivo(rs.getString("activo"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
