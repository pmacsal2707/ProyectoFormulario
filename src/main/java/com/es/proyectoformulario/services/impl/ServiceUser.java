package com.es.proyectoformulario.services.impl;

import com.es.proyectoformulario.model.User;

import java.util.ArrayList;

public class ServiceUser {

    // ATRIBUTOS
    private ArrayList<User> users; // Contiene todos los registros del fichero users.txt
    private GestionFicheroUser gestionUser; // gestion es un objeto para poder llamar a los métodos de GestionFicheroUser
    private ServiceLogger logger;

    private String ruta = "src/main/resources/users/users.txt";

    public ServiceUser() {
        this.users = new ArrayList<>();
        this.gestionUser = new GestionFicheroUser();
        leerFicheroUsers();
        this.logger = new ServiceLogger();
    }

    public boolean altaUsuario(User u) {
        if (u.getId().length() > 20 || u.getName().length() > 20 || u.getPass().length() > 20) {
            return false;
        } else {
            if (u.getId().contains(":") || u.getName().contains(":") || u.getPass().contains(":")) {
                return  false;
            } else {
                if (checkUser(u.getId(), u.getPass())) {
                    return false;
                } else {
                    gestionUser.anadirFichero(u, ruta);
                    users.add(u);
                    return true;
                }
            }
        }
    }
    public boolean checkUser(String idUser, String password) {
        for (int i = 0; i < this.users.size(); i++) {
            User usuario = this.users.get(i); // usuario es el elemento de la posicion i de users
            if (usuario.getId().equalsIgnoreCase(idUser) && usuario.getPass().equals(password)) {
                this.logger.registrarLog(idUser, "LOGIN", "OK");
                return true;
            }
        }
        this.logger.registrarLog(idUser, "LOGIN", "NOT OK");
        return false;
    }

    public boolean userExists(String idUser) {
        return this.users.stream().anyMatch(user -> user.getId().equalsIgnoreCase(idUser));
    }

    public void leerFicheroUsers() {
        this.users = gestionUser.leerFichero(this.ruta);
    }

    public void anadirFicheroUsers(User u) {
        gestionUser.anadirFichero(u, this.ruta);
    }

    public void modificarFicheroUsers() {
        gestionUser.modificarFichero(this.users, this.ruta);
    }

}
