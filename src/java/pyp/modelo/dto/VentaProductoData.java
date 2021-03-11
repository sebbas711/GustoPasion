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
public final class VentaProductoData {
    
    private String nombreCategoria;
    private int cantidadProductoVendida;

    public VentaProductoData() {
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getCantidadProductoVendida() {
        return cantidadProductoVendida;
    }

    public void setCantidadProductoVendida(int cantidadProductoVendida) {
        this.cantidadProductoVendida = cantidadProductoVendida;
    }
    
    
    
}
