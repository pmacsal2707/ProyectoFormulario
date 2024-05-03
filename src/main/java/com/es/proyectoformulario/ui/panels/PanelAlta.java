package com.es.proyectoformulario.ui.panels;

import com.es.proyectoformulario.model.User;
import com.es.proyectoformulario.services.impl.GestionFicheroUser;
import com.es.proyectoformulario.services.impl.ServiceUser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelAlta extends JPanel {

    JTextField user;
    JTextField name;
    JTextField pass;
    JTextField passConfirmation;
    JComboBox isAdmin;
    JButton bEnviar;
    JLabel mensaje = new JLabel();

    ServiceUser serviceUser = new ServiceUser();
    GestionFicheroUser gestionFicheroUser = new GestionFicheroUser();

    MouseListener listenerMouse = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            mensaje.setText(" ");
            if(user.getText().isEmpty() || name.getText().isEmpty() || pass.getText().isEmpty() || passConfirmation.getText().isEmpty()) {
                mensaje.setText("Rellena todos los campos");
            } else {
                if (passConfirmation.getText().equals(pass.getText())) {
                    if (!serviceUser.checkUser(user.getText(), pass.getText())) {
                        if (isAdmin.getSelectedItem().equals("Si")) {
                            User u = new User(user.getText(), name.getText(), pass.getText(), true);
                            serviceUser.altaUsuario(u);
                        } else {
                            User u = new User(user.getText(), name.getText(), pass.getText(), false);
                            serviceUser.altaUsuario(u);
                        }
                    } else {
                        mensaje.setText("El usuario ya existe");
                    }
                } else {
                    mensaje.setText("La contraseña no coincide");
                }
            }
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(135, 206, 250)); // Fondo azul claro
            b.setBorder(new LineBorder(new Color(0, 115, 183), 3)); // Borde azul oscuro
        }
        @Override
        public void mouseExited(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(102, 153, 204)); // Fondo azul medio
            b.setBorder(new LineBorder(new Color(135, 206, 250), 3)); // Borde azul claro
        }
    };
    public PanelAlta() {
        this.setBackground(new Color(174, 139, 225));
        this.setLayout(null);

        JLabel idUser = new JLabel("IDUser: ");
        idUser.setLocation(new Point(150,158));
        idUser.setSize(new Dimension(152,32));
        // usuario.setFont(new Font("Consolas", Font.BOLD, 22));
        this.add(idUser);

        user = new JTextField("Introduzca su IDUser");
        user.setLocation(new Point(250,158));
        user.setSize(new Dimension(152,32));
        this.add(user);

        JLabel nombre = new JLabel("Nombre: ");
        nombre.setLocation(new Point(150,208));
        nombre.setSize(new Dimension(152,32));
        // usuario.setFont(new Font("Consolas", Font.BOLD, 22));
        this.add(nombre);

        name = new JTextField("Introduzca su Nombre");
        name.setLocation(new Point(250,208));
        name.setSize(new Dimension(152,32));
        this.add(name);


        JLabel passwd = new JLabel("Pass: ");
        passwd.setLocation(new Point(150,258));
        passwd.setSize(new Dimension(152,32));
        this.add(passwd);

        pass = new JPasswordField();
        pass.setLocation(new Point(250,258));
        pass.setSize(new Dimension(152,32));
        this.add(pass);

        JLabel passwdConfirmation = new JLabel("Pass: ");
        passwdConfirmation.setLocation(new Point(150,308));
        passwdConfirmation.setSize(new Dimension(152,32));
        this.add(passwdConfirmation);

        passConfirmation = new JPasswordField();
        passConfirmation.setLocation(new Point(250,308));
        passConfirmation.setSize(new Dimension(152,32));
        this.add(passConfirmation);

        JLabel admin = new JLabel("esAdmin: ");
        admin.setLocation(new Point(150,358));
        admin.setSize(new Dimension(152,32));
        this.add(admin);

        isAdmin = new JComboBox<String>();
        isAdmin.addItem("Si");
        isAdmin.addItem("No");
        isAdmin.setLocation(new Point(250,358));
        isAdmin.setSize(new Dimension(152,32));
        this.add(isAdmin);

        mensaje.setLocation(new Point(200,458));
        mensaje.setSize(new Dimension(152, 32));
        this.add(mensaje);

        bEnviar = new JButton("Enviar");
        bEnviar.setLocation(new Point(200,408));
        bEnviar.setSize(new Dimension(152,32));
        this.add(bEnviar);
        bEnviar.addMouseListener(listenerMouse);
    }
}
