/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByimagenP", query = "SELECT p FROM Producto p WHERE p.imagenP = :imagenP")
    , @NamedQuery(name = "Producto.findByEstado", query = "SELECT p FROM Producto p WHERE p.estado = :estado")
    , @NamedQuery(name = "Producto.findByCategoriaProducto", query = "SELECT p FROM Producto p WHERE p.categoriaProducto.id = :categoriaProductoId")})

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "imagenP")
    private String imagenP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "categoria_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriaProducto categoriaProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.LAZY)
    private List<InsumosDelProducto> insumosDelProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetallePedido> detallePedidoList;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String nombre, double precio, String descripcion, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenP() {
        return imagenP;
    }

    public void setImagenP(String rutaImagen) {
       this.imagenP = rutaImagen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pyp.modelo.entidades.Producto[ id=" + id + " ]";
    }

    public List<InsumosDelProducto> getInsumosDelProducto() {
        return insumosDelProducto;
    }

    public void setInsumosDelProducto(List<InsumosDelProducto> insumosDelProducto) {
        this.insumosDelProducto = insumosDelProducto;
    }

    public List<DetallePedido> getDetallePedidoList() {
        return detallePedidoList;
    }

    public void setDetallePedidoList(List<DetallePedido> detallePedidoList) {
        this.detallePedidoList = detallePedidoList;
    }

}
