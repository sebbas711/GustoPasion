/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.dto;

/**
 *
 * @author PC
 */
public class ChartPointProducto {

    private int y;
    private String label;
    private String color;

    public ChartPointProducto() {
    }

    public ChartPointProducto(int y, String label) {
        this.y = y;
        this.label = label;
    }

    public ChartPointProducto(int y, String label, String color) {
        this.y = y;
        this.label = label;
        this.color = color;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
