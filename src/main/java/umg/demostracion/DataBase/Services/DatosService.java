package umg.demostracion.DataBase.Services;

import umg.demostracion.DataBase.Dao.DatosDao;
import umg.demostracion.DataBase.Model.Datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatosService {
    private DatosDao datosDao;

    public DatosService() throws SQLException {
        datosDao = new DatosDao();
    }

    // Lógica para crear un nuevo dato
    public void crearDato(Datos dato) throws SQLException {
        datosDao.crearDato(dato);
    }

    // Lógica para leer un dato por código
    public Datos leerDato(int codigo) throws SQLException {
        return datosDao.leerDato(codigo);
    }

    // Lógica para actualizar un dato
    public void actualizarDato(Datos dato) throws SQLException {
        datosDao.actualizarDato(dato);
    }

    // Lógica para eliminar un dato por código
    public void eliminarDato(int codigo) throws SQLException {
        datosDao.eliminarDato(codigo);
    }

    // Lógica para obtener todos los datos
    public List<Datos> obtenerTodosLosDatos() throws SQLException {
        return datosDao.obtenerTodosLosDatos();
    }
}
