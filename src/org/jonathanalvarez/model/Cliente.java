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
public class Cliente {
    private int clienteId;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String NIT;

    public Cliente() {
    }

    public Cliente(int clienteId, String nombre, String apellido, String telefono, String direccion, String NIT) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.NIT = NIT;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }
    
    @Override
    public String toString() {
        return "Id: " + clienteId + " - " + nombre + " " + apellido;
    }
    
    

    
    
    
}
