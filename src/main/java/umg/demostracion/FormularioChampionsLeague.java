package umg.demostracion;

import umg.demostracion.DataBase.Dao.EquipoChampionsDao;
import umg.demostracion.DataBase.Model.EquipoChampions;
import umg.demostracion.DataBase.Services.EquipoChampionsService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FormularioChampionsLeague {
    private JPanel frmCL;
    private JLabel lblEquipoId;
    private JTextField textFieldEquipoId;
    private JLabel lblNombreEquipo;
    private JTextField textFieldNombreEquipo;
    private JLabel lblPais;
    private JTextField textFieldPais;
    private JLabel lblCiudad;
    private JTextField textFieldCiudad;
    private JLabel lblEstadio;
    private JTextField textFieldEstadio;
    private JLabel lblFundacion;
    private JTextField textFieldFundacion;
    private JLabel lblEntrenador;
    private JLabel lblWebOficial;
    private JTextField textFieldEntrenador;
    private JTextField textFieldWebOficial;
    private JLabel lblFacebook;
    private JTextField textFieldFacebook;
    private JLabel lblRedSocialX;
    private JTextField textFieldRedSocialX;
    private JLabel lblInstagram;
    private JTextField textFieldInstagram;
    private JLabel lblPatrocinadorPrincipal;
    private JTextField textFieldPatrocinadorPrincipal;
    private JButton buttonCrearEquipo;
    private JButton buttonBuscarEquipo;
    private JButton buttonActualizar;
    private JButton buttonEliminar;

    private EquipoChampionsService equipoChampionsService;

    public FormularioChampionsLeague() {
        equipoChampionsService = new EquipoChampionsService();

        buttonCrearEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Convertir fundación a int y validar el rango
                    int fundacion = Integer.parseInt(textFieldFundacion.getText());
                    if (fundacion < 1800 || fundacion > 2023) {
                        JOptionPane.showMessageDialog(null, "El año de fundación debe estar entre 1800 y 2023.");
                        return;
                    }

                    // Crear el equipo
                    EquipoChampions equipo = new EquipoChampions();
                    equipo.setNombre(textFieldNombreEquipo.getText());
                    equipo.setPais(textFieldPais.getText());
                    equipo.setCiudad(textFieldCiudad.getText());
                    equipo.setEstadio(textFieldEstadio.getText());
                    equipo.setFundacion(fundacion);
                    equipo.setEntrenador(textFieldEntrenador.getText());
                    equipo.setWebOficial(textFieldWebOficial.getText());
                    equipo.setFacebook(textFieldFacebook.getText());
                    equipo.setTwitter(textFieldRedSocialX.getText());
                    equipo.setInstagram(textFieldInstagram.getText());
                    equipo.setPatrocinadorPrincipal(textFieldPatrocinadorPrincipal.getText());

                    equipoChampionsService.crearEquipo(equipo);

                    JOptionPane.showMessageDialog(null, "Equipo creado con éxito");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: La fundación debe ser un número válido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el equipo: " + ex.getMessage());
                }
            }
        });

        buttonBuscarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreEquipo = textFieldNombreEquipo.getText();
                    EquipoChampions equipo = equipoChampionsService.buscarEquipoPorNombre(nombreEquipo);

                    if (equipo != null) {
                        textFieldEquipoId.setText(String.valueOf(equipo.getIdEquipo()));
                        textFieldPais.setText(equipo.getPais());
                        textFieldCiudad.setText(equipo.getCiudad());
                        textFieldEstadio.setText(equipo.getEstadio());
                        textFieldFundacion.setText(String.valueOf(equipo.getFundacion()));
                        textFieldEntrenador.setText(equipo.getEntrenador());
                        textFieldWebOficial.setText(equipo.getWebOficial());
                        textFieldFacebook.setText(equipo.getFacebook());
                        textFieldRedSocialX.setText(equipo.getTwitter());
                        textFieldInstagram.setText(equipo.getInstagram());
                        textFieldPatrocinadorPrincipal.setText(equipo.getPatrocinadorPrincipal());

                        JOptionPane.showMessageDialog(null, "Equipo encontrado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el equipo.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar el equipo: " + ex.getMessage());
                }
            }
        });

        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEquipo = Integer.parseInt(textFieldEquipoId.getText());

                    int fundacion = Integer.parseInt(textFieldFundacion.getText());
                    if (fundacion < 1800 || fundacion > 2023) {
                        JOptionPane.showMessageDialog(null, "El año de fundación debe estar entre 1800 y 2023.");
                        return;
                    }

                    EquipoChampions equipo = new EquipoChampions();
                    equipo.setIdEquipo(idEquipo);
                    equipo.setNombre(textFieldNombreEquipo.getText());
                    equipo.setPais(textFieldPais.getText());
                    equipo.setCiudad(textFieldCiudad.getText());
                    equipo.setEstadio(textFieldEstadio.getText());
                    equipo.setFundacion(fundacion);
                    equipo.setEntrenador(textFieldEntrenador.getText());
                    equipo.setWebOficial(textFieldWebOficial.getText());
                    equipo.setFacebook(textFieldFacebook.getText());
                    equipo.setTwitter(textFieldRedSocialX.getText());
                    equipo.setInstagram(textFieldInstagram.getText());
                    equipo.setPatrocinadorPrincipal(textFieldPatrocinadorPrincipal.getText());

                    equipoChampionsService.actualizarEquipo(equipo);

                    JOptionPane.showMessageDialog(null, "Equipo actualizado con éxito.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: La fundación debe ser un número válido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el equipo: " + ex.getMessage());
                }
            }
        });

        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEquipo = Integer.parseInt(textFieldEquipoId.getText());
                    equipoChampionsService.eliminarEquipo(idEquipo);
                    JOptionPane.showMessageDialog(null, "Equipo eliminado con éxito.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: ID del equipo inválido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el equipo: " + ex.getMessage());
                }
            }
        });
    }

    public JPanel getFrmCL() {
        return frmCL;
    }
}


