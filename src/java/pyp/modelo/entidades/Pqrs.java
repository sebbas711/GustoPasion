/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "pqrs")
@NamedQueries({
    @NamedQuery(name = "Pqrs.findAll", query = "SELECT p FROM Pqrs p")
    , @NamedQuery(name = "Pqrs.findById", query = "SELECT p FROM Pqrs p WHERE p.id = :id")
    , @NamedQuery(name = "Pqrs.findByFecha", query = "SELECT p FROM Pqrs p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pqrs.findByObservaciones", query = "SELECT p FROM Pqrs p WHERE p.observaciones = :observaciones")
    , @NamedQuery(name = "Pqrs.findByNombreCliente", query = "SELECT p FROM Pqrs p WHERE p.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Pqrs.findByEmailCliente", query = "SELECT p FROM Pqrs p WHERE p.emailCliente = :emailCliente")
    , @NamedQuery(name = "Pqrs.findByEstado", query = "SELECT p FROM Pqrs p WHERE p.estado = :estado")})
public class Pqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "email_cliente")
    private String emailCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "administrador", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Administrador administrador;
    @JoinColumn(name = "tipoPQRS", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipopqrs tipoPQRS;

    public Pqrs() {
    }

    public Pqrs(Integer id) {
        this.id = id;
    }

    public Pqrs(Integer id, Date fecha, String observaciones, String nombreCliente, String emailCliente, int estado) {
        this.id = id;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
        this.estado = estado;
    }

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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Tipopqrs getTipoPQRS() {
        return tipoPQRS;
    }

    public void setTipoPQRS(Tipopqrs tipoPQRS) {
        this.tipoPQRS = tipoPQRS;
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
        if (!(object instanceof Pqrs)) {
            return false;
        }
        Pqrs other = (Pqrs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pyp.modelo.entidades.Pqrs[ id=" + id + " ]";
    }
    
}
