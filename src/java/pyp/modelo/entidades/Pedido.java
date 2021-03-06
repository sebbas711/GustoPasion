/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM Pedido p WHERE p.id = :id")
    , @NamedQuery(name = "Pedido.findByFecha", query = "SELECT p FROM Pedido p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pedido.findBySubTotal", query = "SELECT p FROM Pedido p WHERE p.subTotal = :subTotal")
    , @NamedQuery(name = "Pedido.findByValorTotal", query = "SELECT p FROM Pedido p WHERE p.valorTotal = :valorTotal")
    , @NamedQuery(name = "Pedido.findByTipoPedido", query = "SELECT p FROM Pedido p WHERE p.tipoPedido = :tipoPedido")
    , @NamedQuery(name = "Pedido.findByTelefono", query = "SELECT p FROM Pedido p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Pedido.findByPuntoEntrega", query = "SELECT p FROM Pedido p WHERE p.puntoEntrega = :puntoEntrega")
    , @NamedQuery(name = "Pedido.findByObservaciones", query = "SELECT p FROM Pedido p WHERE p.observaciones = :observaciones")
    , @NamedQuery(name = "Pedido.findByEstadoPedido", query = "SELECT p FROM Pedido p WHERE p.estadoPedido.id = :estadoPedidoId")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total")
    private double subTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private double valorTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_pedido")
    private String tipoPedido;
    @Column(name = "telefono")
    private Long telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "punto_entrega")
    private String puntoEntrega;
    @Size(min = 1, max = 65535)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<DetallePedido> detallesPedido;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;
    @JoinColumn(name = "estadoPedido", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estadopedido estadoPedido;

    public Pedido() {
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Pedido(Integer id, Date fecha, double subTotal, double valorTotal, String tipoPedido, String puntoEntrega) {
        this.id = id;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.valorTotal = valorTotal;
        this.tipoPedido = tipoPedido;
        this.puntoEntrega = puntoEntrega;
    }

    public void calcularTotales() {
        subTotal = 0;
        for(DetallePedido detallePedido: detallesPedido){
            subTotal += detallePedido.getCantidad() * detallePedido.getValorUnitario();
        }
        valorTotal = subTotal + (subTotal * 0.16);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public double getSubTotal() {
        return subTotal;
    }
    
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public String getTipoPedido() {
        return tipoPedido;
    }
    
    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }
    
    public Long getTelefono() {
        return telefono;
    }
    
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    
    public String getPuntoEntrega() {
        return puntoEntrega;
    }
    
    public void setPuntoEntrega(String puntoEntrega) {
        this.puntoEntrega = puntoEntrega;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }
    
    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Estadopedido getEstadoPedido() {
        return estadoPedido;
    }
    
    public void setEstadoPedido(Estadopedido estadoPedido) {
        this.estadoPedido = estadoPedido;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "pyp.modelo.entidades.Pedido[ id=" + id + " ]";
    }
    //</editor-fold>
    
}
