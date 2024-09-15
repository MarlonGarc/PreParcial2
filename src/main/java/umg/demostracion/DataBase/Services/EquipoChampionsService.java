package umg.demostracion.DataBase.Services;

import umg.demostracion.DataBase.Conexion.Conexion;
import umg.demostracion.DataBase.Dao.EquipoChampionsDao;
import umg.demostracion.DataBase.Model.EquipoChampions;

import java.sql.Connection;
import java.sql.SQLException;

public class EquipoChampionsService {
    private EquipoChampionsDao equipoChampionsDao;

    public EquipoChampionsService() {
        try {
            Connection connection = Conexion.getConnection();
            equipoChampionsDao = new EquipoChampionsDao(connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    public void crearEquipo(EquipoChampions equipo) throws SQLException {
        equipoChampionsDao.crearEquipo(equipo);
    }

    public EquipoChampions buscarEquipoPorNombre(String nombre) throws SQLException {
        return equipoChampionsDao.buscarEquipoPorNombre(nombre);
    }

    public void actualizarEquipo(EquipoChampions equipo) throws SQLException {
        equipoChampionsDao.actualizarEquipo(equipo);
    }

    public void eliminarEquipo(int idEquipo) throws SQLException {
        equipoChampionsDao.eliminarEquipo(idEquipo);
    }
}