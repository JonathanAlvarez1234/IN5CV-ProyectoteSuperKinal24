/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jonathanalvarez.model;

/**
 *
 * @author informatica
 */
public class Cargo {
    private int cargoId;
    private int nombreCargo;
    private int descripcionCargo;

    public Cargo() {
    }

    public Cargo(int cargoId, int nombreCargo, int descripcionCargo) {
        this.cargoId = cargoId;
        this.nombreCargo = nombreCargo;
        this.descripcionCargo = descripcionCargo;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public int getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(int nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public int getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(int descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    @Override
    public String toString() {
        return "Cargo{" + "cargoId=" + cargoId + ", nombreCargo=" + nombreCargo + ", descripcionCargo=" + descripcionCargo + '}';
    }
    
    
}
