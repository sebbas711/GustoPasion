/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "insumos_del_producto")
@NamedQueries({
    @NamedQuery(name = "InsumosDelProducto.findAll", query = "SELECT i FROM InsumosDelProducto i")
    , @NamedQuery(name = "InsumosDelProducto.findById", query = "SELECT i FROM InsumosDelProducto i WHERE i.id = :id")
    , @NamedQuery(name = "InsumosDelProducto.findByCantidadInsumo", query = "SELECT i FROM InsumosDelProducto i WHERE i.cantidadInsumo = :cantidadInsumo")})
public class InsumosDelProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad_insumo")
    private Double cantidadInsumo;
    @JoinColumn(name = "insumo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Insumo insumo;
    @JoinColumn(name = "producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto producto;

    public InsumosDelProducto() {
    }

    public InsumosDelProducto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(Double cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumosDelProducto)) {
            return false;
        }
        InsumosDelProducto other = (InsumosDelProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pyp.modelo.entidades.InsumosDelProducto[ id=" + id + " ]";
    }

    public void setCantidadInsumo(Integer cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
