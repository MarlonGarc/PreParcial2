package umg.demostracion.DataBase.Dao;

import umg.demostracion.DataBase.Model.EquipoChampions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoChampionsDao {
    private Connection connection;

    public EquipoChampionsDao(Connection connection) {
        this.connection = connection;
    }

    public void crearEquipo(EquipoChampions equipo) throws SQLException {
        String sql = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getPais());
            stmt.setString(3, equipo.getCiudad());
            stmt.setString(4, equipo.getEstadio());
            stmt.setInt(5, equipo.getFundacion());
            stmt.setString(6, equipo.getEntrenador());
            stmt.setString(7, equipo.getWebOficial());
            stmt.setString(8, equipo.getFacebook());
            stmt.setString(9, equipo.getTwitter());
            stmt.setString(10, equipo.getInstagram());
            stmt.setString(11, equipo.getPatrocinadorPrincipal());
            stmt.executeUpdate();
        }
    }

    public EquipoChampions buscarEquipoPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM equipos_champions WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEquipoChampions(rs);
            }
        }
        return null;
    }

    public void actualizarEquipo(EquipoChampions equipo) throws SQLException {
        String sql = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getPais());
            stmt.setString(3, equipo.getCiudad());
            stmt.setString(4, equipo.getEstadio());
            stmt.setInt(5, equipo.getFundacion());
            stmt.setString(6, equipo.getEntrenador());
            stmt.setString(7, equipo.getWebOficial());
            stmt.setString(8, equipo.getFacebook());
            stmt.setString(9, equipo.getTwitter());
            stmt.setString(10, equipo.getInstagram());
            stmt.setString(11, equipo.getPatrocinadorPrincipal());
            stmt.setInt(12, equipo.getIdEquipo());
            stmt.executeUpdate();
        }
    }

    public void eliminarEquipo(int idEquipo) throws SQLException {
        String sql = "DELETE FROM equipos_champions WHERE id_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEquipo);
            stmt.executeUpdate();
        }
    }

    private EquipoChampions mapResultSetToEquipoChampions(ResultSet rs) throws SQLException {
        EquipoChampions equipo = new EquipoChampions();
        equipo.setIdEquipo(rs.getInt("id_equipo"));
        equipo.setNombre(rs.getString("nombre"));
        equipo.setPais(rs.getString("pais"));
        equipo.setCiudad(rs.getString("ciudad"));
        equipo.setEstadio(rs.getString("estadio"));
        equipo.setFundacion(rs.getInt("fundacion"));
        equipo.setEntrenador(rs.getString("entrenador"));
        equipo.setWebOficial(rs.getString("web_oficial"));
        equipo.setFacebook(rs.getString("facebook"));
        equipo.setTwitter(rs.getString("twitter"));
        equipo.setInstagram(rs.getString("instagram"));
        equipo.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
        return equipo;
    }
}
