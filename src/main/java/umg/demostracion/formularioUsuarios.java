package umg.demostracion;

import umg.demostracion.DataBase.Model.Usuario;
import umg.demostracion.DataBase.Services.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class formularioUsuarios {
    private JLabel lblIdUsuario;
    private JTextField textFieldIdUsuario;
    private JLabel lblCarne;
    private JTextField textFieldCarne;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JLabel lblCorreo;
    private JTextField textFieldCorreo;
    private JLabel lblSeccion;
    private JTextField textFieldSeccion;
    private JLabel lblTelegramId;
    private JTextField textFieldTelegramId;
    private JLabel lblActivo;
    private JTextField textFieldActivo;
    private JButton buttonCrear;
    private JButton buttonLeer;
    private JButton buttonActualizar;
    private JButton buttonEliminar;
    private JPanel jPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("formularioUsuarios");
        frame.setContentPane(new formularioUsuarios().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private UsuarioService usuarioService;

    public formularioUsuarios() {
        usuarioService = new UsuarioService();

        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario();
                usuario.setNombre(textFieldNombre.getText());
                usuario.setCorreo(textFieldCorreo.getText());
                usuario.setSeccion(textFieldSeccion.getText());
                usuario.setTelegramId(Long.parseLong(textFieldTelegramId.getText()));
                usuario.setActivo(textFieldActivo.getText());
                try {
                    usuarioService.crearUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el usuario " + ex.getMessage());
                }

            }
        });
        buttonLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int idUsuario = Integer.parseInt(textFieldIdUsuario.getText());
                    Usuario usuario = usuarioService.leerUsuarioPorId(idUsuario);
                    if (usuario != null) {
                        textFieldCarne.setText(usuario.getCarne());
                            textFieldCarne.setText(usuario.getCarne());
                            textFieldNombre.setText(usuario.getNombre());
                            textFieldCorreo.setText(usuario.getCorreo());
                            textFieldSeccion.setText(usuario.getSeccion());
                            textFieldTelegramId.setText(String.valueOf(usuario.getTelegramId()));
                            textFieldActivo.setText(usuario.getActivo());

                        } else{
                            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                        }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID valido" + ex.getMessage());
                } catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Error al leer el usuario" + ex.getMessage());
                }
            }
        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario();
                usuario.setNombre(textFieldNombre.getText());
                usuario.setCorreo(textFieldCorreo.getText());
                usuario.setSeccion(textFieldSeccion.getText());
                usuario.setTelegramId(Long.parseLong(textFieldTelegramId.getText()));
                usuario.setActivo(textFieldActivo.getText());

                try {
                    usuarioService.actualizarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
                }catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el usuario" + ex.getMessage());
                }
            }
        });
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = textFieldCorreo.getText();
                try {
                    usuarioService.eliminarUsuario(correo);
                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el usuario" + ex.getMessage());
                }

            }
        });
    }
    public JPanel getPanel() {
        return jPanel;
    }
}
