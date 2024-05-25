/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jonathanalvarez.model;

/**
 *
 * @author HP
 */
public class Usuario {
    private int usuarioId;
    private String usuario;
    private String pass;
    private int nivelAccesoId;
    private int empleadosId;

    public Usuario() {
    }

    public Usuario(int usuarioId, String usuario, String pass, int nivelAccesoId, int empleadosId) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.pass = pass;
        this.nivelAccesoId = nivelAccesoId;
        this.empleadosId = empleadosId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getNivelAccesoId() {
        return nivelAccesoId;
    }

    public void setNivelAccesoId(int nivelAccesoId) {
        this.nivelAccesoId = nivelAccesoId;
    }

    public int getEmpleadosId() {
        return empleadosId;
    }

    public void setEmpleadosId(int empleadosId) {
        this.empleadosId = empleadosId;
    }

    
    
    @Override
    public String toString() {
        return "Id: " + usuarioId + " | " + usuario;
    }
}
