package umg.demostracion.DataBase.Services;

import umg.demostracion.DataBase.Dao.UsuarioDao;
import umg.demostracion.DataBase.Model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
    }

    // Lógica para crear un nuevo usuario
    public void crearUsuario(Usuario usuario) throws SQLException {
        if (usuarioDao.leerUsuarioPorCorreo(usuario.getCorreo()) != null) {
            throw new SQLException(" El correo ya está en uso.");
        }
        usuarioDao.crearUsuario(usuario);
    }

    // Lógica para leer un usuario por correo
    public Usuario leerUsuarioPorCorreo(String correo) throws SQLException {
        return usuarioDao.leerUsuarioPorCorreo(correo);
    }
    // Lógica para leer un usuario por correo

    public Usuario leerUsuarioPorId (int id ) throws SQLException {
        return usuarioDao.leerUsuarioPorId(id);
    }

    // Lógica para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDao.actualizarUsuario(usuario);
    }

    // Lógica para eliminar un usuario por correo
    public void eliminarUsuario(String correo) throws SQLException {
        usuarioDao.eliminarUsuario(correo);
    }

    // Lógica para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        return usuarioDao.obtenerTodosLosUsuarios();
    }
}
