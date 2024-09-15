package umg.demostracion;


import umg.demostracion.DataBase.Model.Datos;
import umg.demostracion.DataBase.Services.DatosService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class formularioCRUD {
    private JPanel FormularioDatos;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JLabel lblApellido;
    private JTextField textFieldApellido;
    private JLabel lblDepartamento;
    private JTextField textFieldDepartamento;
    private JLabel lblFechaNacimiento;
    private JTextField textFieldFechaNacimiento;
    private JButton buttonCrear;
    private JButton buttonLeer;
    private JButton buttonActualizar;
    private JButton buttonEliminar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("formularioCRUD");
        frame.setContentPane(new formularioCRUD().FormularioDatos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private DatosService datosService;

    public formularioCRUD() {
        try {
            datosService = new DatosService();

            // Configura los botones
            buttonCrear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Datos dato = new Datos();
                        dato.setNombre(textFieldNombre.getText());
                        dato.setApellido(textFieldApellido.getText());
                        dato.setDepartamento(textFieldDepartamento.getText());
                        dato.setFechaNacimiento(java.sql.Date.valueOf(textFieldFechaNacimiento.getText()));
                        //validar la fecha
                        String fechaTexto = textFieldFechaNacimiento.getText();
                        Date fechaNacimiento = Date.valueOf(fechaTexto); //convierte el texto a tipo date
                        dato.setFechaNacimiento(fechaNacimiento);
                        datosService.crearDato(dato);
                        JOptionPane.showMessageDialog(null, "Dato creado exitosamente");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al crear dato");
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto use el formato YYYY-MM-DD");
                    }
                }
            });

            buttonLeer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese código"));
                        Datos dato = datosService.leerDato(codigo);
                        if (dato != null) {
                            textFieldNombre.setText(dato.getNombre());
                            textFieldApellido.setText(dato.getApellido());
                            textFieldDepartamento.setText(dato.getDepartamento());
                            textFieldFechaNacimiento.setText(dato.getFechaNacimiento().toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Dato no encontrado");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al leer dato");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Código inválido");
                    }
                }
            });

            buttonActualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese código"));
                        Datos dato = new Datos();
                        dato.setCodigo(codigo);
                        dato.setNombre(textFieldNombre.getText());
                        dato.setApellido(textFieldApellido.getText());
                        dato.setDepartamento(textFieldDepartamento.getText());
                        dato.setFechaNacimiento(java.sql.Date.valueOf(textFieldFechaNacimiento.getText()));
                        datosService.actualizarDato(dato);
                        JOptionPane.showMessageDialog(null, "Dato actualizado exitosamente");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al actualizar dato");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Código inválido");
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto");
                    }
                }
            });

            buttonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese código"));
                        datosService.eliminarDato(codigo);
                        JOptionPane.showMessageDialog(null, "Dato eliminado exitosamente");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar dato");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Código inválido");
                    }
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos");
        }
    }

    public JPanel getPanel() {
        return FormularioDatos;
    }
}