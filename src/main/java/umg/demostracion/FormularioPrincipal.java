package umg.demostracion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPrincipal {
    private JPanel frmPrincipal;
    private JLabel lblEncabezado;
    private JLabel lblIndicaciones;
    private JLabel lblvacio;
    private JButton buttonEjercicio1;
    private JButton buttonEjercicio2;
    private JLabel lblVacio2;
    private JButton buttonEjercicio3;

    public FormularioPrincipal() {

        buttonEjercicio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // abrir frm ejercicio 1
                JFrame frame = new JFrame("Formulario CRUD");
                frame.setContentPane(new formularioCRUD().getPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        buttonEjercicio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // abrir frm ejercicio 2
                JFrame frame = new JFrame("Formulario Usuarios");
                frame.setContentPane(new formularioUsuarios().getPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        buttonEjercicio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // abrir frm ejercicio 3
                JFrame frame = new JFrame("Formulario Champions League");
                frame.setContentPane(new FormularioChampionsLeague().getFrmCL());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario Principal");
        frame.setContentPane(new FormularioPrincipal().frmPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
