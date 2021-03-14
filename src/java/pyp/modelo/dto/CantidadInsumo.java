/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.dto;

/**
 *
 * @author PC
 */
public final class CantidadInsumo {

    private String nombreInsumo;
    private String mes;
    private double cantidadInsumo;

    public CantidadInsumo() {
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public double getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(double cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

}
