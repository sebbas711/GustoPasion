/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.dto;

/**
 *
 * @GAES 1
 */
public final class PorcentajePQRS {
    
    private int anio;
    private int mes;
    private double totalMes;

    public PorcentajePQRS() {
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getTotalMes() {
        return totalMes;
    }

    public void setTotalMes(double totalMes) {
        this.totalMes = totalMes;
    }
    
    
}
